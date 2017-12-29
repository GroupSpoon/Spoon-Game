package edu.swarthmore.cs.spoon.client.network;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import edu.swarthmore.cs.spoon.client.network.interfaces.GameClient;
import edu.swarthmore.cs.spoon.client.network.interfaces.StartGameListener;
import edu.swarthmore.cs.spoon.client.network.model.PlayerCharacterClient;
import edu.swarthmore.cs.spoon.client.network.model.WorldClient;
import edu.swarthmore.cs.spoon.common.Wait;
import edu.swarthmore.cs.spoon.common.WaitableCounter;
import edu.swarthmore.cs.spoon.common.messages.*;
import edu.swarthmore.cs.spoon.common.model.ThingListenersHolderImpl;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class GameClientImpl implements GameClient {
    private final Logger logger = LoggerFactory.getLogger(GameClientImpl.class);
    private Map<Integer, PlayerCharacterClient> characterList = new HashMap();
    private ThingListenersHolder thingListenersHolder = new ThingListenersHolderImpl();
    private Worker worker;
    private Client client;
    private int clientId;
    private Wait wait;
    private WaitableCounter playerCounter;
    private WorldClient world;
    private boolean inPlay = false;
    int nextMessageId = 10;
    private Map<Integer, SynchronousQueue<Requests.IdentifierResponse>> waitMap;
    private List<StartGameListener> startGameListeners = new ArrayList<>();
    private List<MovementListener> movementListeners = new ArrayList<>();
    private List<ThingEnteredLocListener> locListeners = new ArrayList<>();
    public GameClientImpl(String ip) throws IOException {
        this(ip, new Wait());
    }

    public GameClientImpl(String ip, StartGameListener listener) throws IOException {
        this(ip, new Wait());
        this.addStartGameListener(listener);
    }

    @Override
    public ThingListenersHolder getThingListenersHoler() {
        return thingListenersHolder;
    }

    public GameClientImpl(String ip, Wait wait) throws IOException {
        this.playerCounter = new WaitableCounter();
        this.client = new Client();
        this.wait = wait;
        waitMap = Collections.synchronizedMap(new HashMap<>());
        Kryo kryo = client.getKryo();
        Utils.register(kryo);
        worker = new Worker();
        worker.start();
        //  worker.run();
        Thread clientThread = new Thread(client);
        clientThread.start();
        world = new WorldClient(this);
        client.connect(5000, ip, 54555, 54777);
        client.addListener(new Listener() {


            public void received(Connection connection, Object object) {
                //logger.info("The server has sent me a message of some kind");
                //System.out.printf("Client %d entering listener\n", GameClientImpl.this.clientId);
                if (object instanceof Requests.Success) {
                    wait.ready();
                    System.out.println("Connected Successfully!");
                    Requests.Success success = (Requests.Success) object;
                    clientId = success.id;
                    setId(clientId);
                    System.out.println(clientId);
                    Requests.NewCharacter character = new Requests.NewCharacter();
                    character.id = clientId;
                    character.name = "Spork";
                    client.sendTCP(character);
                }

                if (object instanceof Requests.Character) {
                    System.out.println("A new character has entered the world!");
                    Requests.Character character = (Requests.Character) object;

                    worker.addWork(() -> {
                        PlayerCharacterClient realCharacter = (PlayerCharacterClient) world.createCharacter(character.name, character.id);
                        characterList.put(character.id, realCharacter);
                        playerCounter.increment();
                    });
                }

                if (object instanceof Requests.Movement) {
                    Requests.Movement movement = (Requests.Movement) object;
                    for (MovementListener listener : movementListeners) {
                        listener.thingMoved(movement.id, movement.position);
                    }
                }
                if (object instanceof Requests.NewLocation) {
                    Requests.NewLocation newLocation = (Requests.NewLocation) object;
                    world.createNewLoc(newLocation.name, newLocation.id, newLocation.height, newLocation.width);
                }
                if (object instanceof Requests.IdentifierResponse) {
                    Requests.IdentifierResponse identifierResponse = (Requests.IdentifierResponse) object;
                    int id = identifierResponse.id;
                    //logger.info("Identifier response {} reached me", id);
                    try {
                        waitMap.get(id).put(identifierResponse);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                }
                if (object instanceof Requests.StartPlay) {
                    Requests.StartPlay startPlay = (Requests.StartPlay) object;
                    worker.addWork(() -> {
                        startGame(startPlay.numPlayers);
                    });

                }
                if (object instanceof Requests.NewThing) {

                    Requests.NewThing thing = (Requests.NewThing) object;
                    logger.info("I have been asked to create a new thing, " + thing.name);
                    Location loc = world.getLocationMap().get(thing.locId);
                    worker.addWork(() -> {
                        logger.info("I am now creating a new thing, " + thing.name);
                        world.createThing(thing.name, thing.thingId, loc, thing.pos, thing.type);});
                }
                if (object instanceof  Requests.ActionEnded) {
                    Requests.ActionEnded actionEnded = (Requests.ActionEnded) object;
                    characterList.get(actionEnded.pcId).actionEnded(actionEnded.actionId);
                }
                if (object instanceof Requests.MovStateUpdate) {
                    Requests.MovStateUpdate movStateUpdate = (Requests.MovStateUpdate) object;
                    characterList.get(movStateUpdate.thingId).notifyMovStateListeners(movStateUpdate.dir);
                }
                //System.out.printf("Client %d exiting listener\n", GameClientImpl.this.clientId);


            }
        });
        client.sendTCP(new Requests.JoinGame());


    }

    private void setId(int id) {
        this.clientId = id;
    }

    public World getWorld() {
        return world;
    }

    public Requests.IdentifierResponse sendMessageandGetReply(Requests.IdentifierMessage message) {
        //logger.info("sending identifier message {}.", this.nextMessageId);
        message.id = this.nextMessageId;
        nextMessageId++;
        SynchronousQueue<Requests.IdentifierResponse> msgQueue = new SynchronousQueue<>();
        waitMap.put(message.id, msgQueue);
        client.sendTCP(message);
        try {
            Requests.IdentifierResponse response = msgQueue.take();
            waitMap.remove(response.id);
            //logger.info("received identifier response {}", response.id);
            return response;
        } catch (InterruptedException e) {
            //logger.info("I have been interrupted!");
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(Message message) {
        //logger.info("Sending server a message, don't care about reply");
        client.sendTCP(message);
    }

    @Override
    public int getOwnId() {

        Requests.IdentifierResponse id = sendMessageandGetReply(new IdentifierMessage.GetId());
        return ((IdentifierResponse.SendInt) id).intToSend;
    }

    @Override
    public void stopGame() {
        worker.stop();
        client.stop();
    }

    @Override
    public void addStartGameListener(StartGameListener startGameListener) {
        startGameListeners.add(startGameListener);
        if (inPlay) {
            System.out.println("I'm already ready");
            startGameListener.startGame(world);
        }
    }

    @Override
    public void startGame(int numPlayers) {
        while (true) {
            if (numPlayers == world.getCharacters().values().size()) {
                System.out.println("NOTIFYING ALL LISTENERS");
                for (StartGameListener startGameListener : startGameListeners) {
                    startGameListener.startGame(world);

                }
                inPlay = true;
                break;
            } else {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //do nothing
                }
            }
        }

    }

    @Override
    public void addMovementListener(MovementListener movementListener) {
        movementListeners.add(movementListener);
    }

    @Override
    public void waitForNPlayers(int n) {
        this.playerCounter.waitFor(n);
    }

    @Override
    public void addLocationListener(ThingEnteredLocListener locListener) {
        locListeners.add(locListener);
    }

    private class Worker implements Runnable {
        Thread thread;
        BlockingQueue<Runnable> workQueue;
        boolean stop;

        public Worker() {
            thread = new Thread(this);
            workQueue = new LinkedBlockingQueue<>();
            stop = false;
        }

        @Override
        public void run() {
            while (!stop) {
                try {
                    Runnable work = workQueue.poll(50, TimeUnit.MILLISECONDS);
                    if (work != null) {
                        work.run();
                    }
                } catch (InterruptedException e) {
                    //do nothing
                }
            }
        }

        public void start() {
            thread.start();
//            this.run();
        }

        public void stop() {
            this.stop = true;
            thread.interrupt();
        }

        public void addWork(Runnable work) {
            workQueue.add(work);
        }
    }
}
