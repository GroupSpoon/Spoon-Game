package edu.swarthmore.cs.spoon.network;

import edu.swarthmore.cs.spoon.client.network.GameClientImpl;
import edu.swarthmore.cs.spoon.common.Wait;
import edu.swarthmore.cs.spoon.common.messages.Requests;
import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.model.implementations.ActionImpl;
import edu.swarthmore.cs.spoon.model.implementations.WorldImpl;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import edu.swarthmore.cs.spoon.client.network.interfaces.GameClient;
import edu.swarthmore.cs.spoon.server.classes.GameManagerImp;
import edu.swarthmore.cs.spoon.server.interfaces.GameManager;
import edu.swarthmore.cs.spoon.server.interfaces.TimeListener;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class NetworkTests {
    GameManager manager;
    GameClient client1;
    GameClient client2;
    Thread serverT;
    Thread clientT1;
    Thread clientT2;
    TimeListener timeListener;

    private boolean startGame(Wait serverWait) throws IOException {
        try {
            manager = new GameManagerImp();
            manager.startGame();
            serverWait.ready();
            manager.playGame();
            return true;
        } catch (IOException e) {
            System.out.println("Hmm, couldn't get the server running. " +
                    "That's kinda odd. I'd tell you why, but I'm not 100% " +
                    "on what a sever IS.");
            throw e;
        }
    }

    private GameClient getGameClient(Wait clientWait) throws IOException {
        try {
            return new GameClientImpl("127.0.0.1", clientWait);
        } catch (IOException e) {
            System.out.println("Couldn't connect to the server");
            throw e;
        }
    }

    ;

    @Before
    public void setUp() {
        Wait serverWait = new Wait();
        serverT = new Thread(() -> {
            try {

                startGame(serverWait);
            } catch (IOException e) {
                fail("Failed to set up server");
            }
        });
        serverT.start();
        serverWait.pause();
        Wait client1Wait = new Wait();
        clientT1 = new Thread(() -> {
            try {
                client1 = getGameClient(client1Wait);
            } catch (IOException e) {
                fail("Failed to create client1");
            }
        });
        clientT1.start();
        client1Wait.pause();
        Wait client2Wait = new Wait();
        clientT2 = new Thread(() -> {
            try {
                client2 = getGameClient(client2Wait);

            } catch (IOException e) {
                fail("Failed to create client2");
            }
        });
        clientT2.start();
        client2Wait.pause();
        client1.waitForNPlayers(2);
        client2.waitForNPlayers(2);
    }

    @After
    public void cleanUp() {
        manager.stopGame();
    }

    @Test
    public void testAddingPlayers() {

        World world = client2.getWorld();
        Collection<PlayerCharacter> characters = world.getCharacters().values();

        assertEquals(2, characters.size());
    }

    @Test
    public void testMovState() {
        World serverWorld = manager.getWorld();
        World client1world = client1.getWorld();
        Requests.MovState movState = new Requests.MovState();
        movState.direction = Direction.UP;
        client1.sendMessage(movState);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            //do nothing
        }
        Collection<PlayerCharacter> characterList = serverWorld.getCharacters().values();
        assertEquals(Direction.UP, characterList.iterator().next().getMovState());


    }

    @Test
    public void testTimeStepMovement() {
        World world = client1.getWorld();
        manager.pause();
        PlayerCharacter character = world.getCharacters().get(1);
        character.setMovState(Direction.DOWN);
        int startY = character.getPosition().getULY();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        manager.addTimeListener(new TimeListener() {
            @Override
            public void timeStep() {
                countDownLatch.countDown();
            }
        });
        manager.resume();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            fail("Just try again idk, couldn't count down 2 timesteps");
        }
        manager.pause();
        assertEquals(startY + 2, character.getPosition().getULY());


    }

    @Test
    public void sendingInt() {
        World serverWorld = manager.getWorld();
        World clientWorld = client1.getWorld();
        PlayerCharacter serverChar = serverWorld.getCharacters().get(1);
        serverChar.modifySpoons(10);
        int id = serverChar.getId();
        assertEquals(10, clientWorld.getCharacter(id).getSpoons());
    }

    @Test
    public void testGettingId() {
        assertEquals(client1.getOwnId(), 1);
    }

    @Test
    public void getThingsfromLocation() {
        World serverWorld = manager.getWorld();
        World clientWorld = client1.getWorld();
        int locId = serverWorld.getLocationMap().keySet().iterator().next();
        assertEquals(serverWorld.getLocationMap().get(locId).getThings().size(), clientWorld.getLocationMap().get(locId).getThings().size());

    }

    @Test
    public void examineThing() {
        World serverWorld = manager.getWorld();
        World clientWorld = client2.getWorld();
        int id = 100;
        for (Thing thing: serverWorld.getThings().values()) {
            if (thing.getId() > 99) {
                id = thing.getId();
                break;
            }
        }
        assertEquals(serverWorld.getThings().get(id).examine(serverWorld.getCharacter(1)).size(), clientWorld.getThings().get(id).examine(clientWorld.getCharacter(1)).size());

    }
    @Test
    public void createNewThing(){
        WorldImpl serverWorld = (WorldImpl) manager.getWorld();
        World clientWorld = client2.getWorld();
        Location location = serverWorld.getLocationMap().values().iterator().next();
        System.out.println(location.getLocationID() + "location id");
        System.out.println(serverWorld.getThings().values().size());
        Thing thing = serverWorld.createThing("hello!",  location, new PositionImpl(0,0,20,20), ThingType.ITEM);
        location.addThing(thing);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //do nothing
        }
        System.out.println(thing.getId());
        Thing clientThing = clientWorld.getThings().get(thing.getId());
        System.out.println(clientThing.getId());
        assertEquals(serverWorld.getThings().values().size(), clientWorld.getThings().values().size());
    }
    @Test
    public void getLocation() {
        WorldImpl serverWorld = (WorldImpl) manager.getWorld();
        World clientWorld = client2.getWorld();
        Thing thing = serverWorld.getThings().get(1);
        Thing clientThing = clientWorld.getThings().get(thing.getId());
        assertEquals(thing.getLocation().getLocationID(), clientThing.getLocation().getLocationID());
    }
    @Test
    public void takeAction() {
        WorldImpl serverWorld = (WorldImpl) manager.getWorld();
        World clientWorld = client1.getWorld();
        System.out.println(clientWorld.getThings().size());
        PlayerCharacter pc = clientWorld.getCharacter(1);
        Location location = serverWorld.getLocationMap().values().iterator().next();
        Thing thing = serverWorld.createThing("hello!", location, new PositionImpl(0,0,20,20), ThingType.ITEM);
        Action action = new ActionImpl("test", thing, 10, null, 1);
        thing.addAction(action);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            //do nothing
        }
        System.out.println(clientWorld.getThings().size());
        Thing clientThing = clientWorld.getThings().get(thing.getId());
        manager.pause();
        pc.initiateAction(action, new ActionEndListener() {
            @Override
            public void actionEnded(int actionId, boolean success) {

            }
        });
        CountDownLatch countDownLatch = new CountDownLatch(2);
        manager.addTimeListener(new TimeListener() {
            @Override
            public void timeStep() {
                countDownLatch.countDown();
            }
        });
        manager.resume();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            fail("Just try again idk, couldn't count down 2 timesteps");
        }
        manager.pause();
        assertEquals(thing.getPosition().getPointsAsInts(), clientThing.getPosition().getPointsAsInts());

    }

}
