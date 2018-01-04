package edu.swarthmore.cs.spoon.server.classes;
import edu.swarthmore.cs.spoon.common.messages.*;
import edu.swarthmore.cs.spoon.model.implementations.WorldImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.swarthmore.cs.spoon.model.interfaces.*;
import edu.swarthmore.cs.spoon.server.interfaces.GameManager;
import edu.swarthmore.cs.spoon.server.interfaces.GameState;
import edu.swarthmore.cs.spoon.server.interfaces.TimeListener;

public class GameManagerImp implements GameManager {
    int numPlayers;
    private final Logger logger = LoggerFactory.getLogger(GameManagerImp.class);
    Server server;
    Map<Integer, PlayerCharacter>  characterList = new HashMap();
    Map<Integer, Thing>  thingMap = new HashMap();
    Map<Integer, Location> locationMap = new HashMap<>();
    Map connectionList = new HashMap();
    World world;
    GameState state = GameState.WAITING;
    List<TimeListener> timeListeners = new ArrayList<>();
    public GameManagerImp(){};
    public GameManagerImp(TimeListener timeListener){
        this.addTimeListener(timeListener);
    }
    @Override
    public void saveGame() {

    }

    @Override
    public void loadGame() {

    }

    @Override
    public void startGame() throws IOException {

        numPlayers = 0;
        world = new WorldImpl();
        server = new com.esotericsoftware.kryonet.Server();
        world.addNewThingListener(new ThingListenerImpl(server, this));
        Kryo kryo = server.getKryo();
        Utils.register(kryo);
        server.start();
        server.bind(54555, 54777);
        locationMap = world.getLocationMap();

        for (Location location: locationMap.values()) {
            List<Thing> things = location.getThings();
            for (Thing thing: things) {
                thingMap.put(thing.getId(), thing);
                thing.addMovementListener(new MovementListenerImp(server, thing.getId()));
                thing.addThingEnteredLocListener(new ThingEnteredLocListenerImp(server));
            }
        }

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                //logger.info("I have received a message from client {}", connection.getID());
                if (object instanceof Requests.JoinGame) {
                    Requests.Success success = new Requests.Success();
                    success.id = connection.getID();
                    System.out.println(success.id);
                    connectionList.put(numPlayers, connection);
                    connection.sendTCP(success);
                    for (Location location: locationMap.values()) {
                        Requests.NewLocation newLocation = new Requests.NewLocation();
                        newLocation.id = location.getLocationID();
                        newLocation.name = location.getLocationName();
                        newLocation.height = location.getLocationHeight();
                        newLocation.width = location.getLocationWidth();
                        connection.sendTCP(newLocation);
                    }
                    for (Thing thing: thingMap.values()) {
                        Requests.NewThing newThing = new Requests.NewThing();
                        newThing.thingId = thing.getId();
                        newThing.locId = thing.getLocation().getLocationID();
                        newThing.type = thing.getThingType();
                        newThing.pos = thing.getPosition();
                        newThing.name = thing.getName();
                        connection.sendTCP(newThing);
                    }
                }
                if (object instanceof Requests.NewCharacter) {
                    Requests.NewCharacter charSketch = (Requests.NewCharacter) object;
                    PlayerCharacter character = world.createCharacter(charSketch.name, charSketch.id);
                    character.addMovementListener(new MovementListenerImp(server, charSketch.id));
                    character.addMovStateChangeListener(new MovStateChangeListenerImp(server));
                    characterList.put(charSketch.id, character);
                    thingMap.put(charSketch.id, character);
                    System.out.println("character joined");
                    Requests.Character sendCharacter = new Requests.Character();
                    sendCharacter.position = character.getPosition();
                    sendCharacter.id = character.getId();
                    sendCharacter.name = character.getName();
                    server.sendToAllExceptTCP(connection.getID(), sendCharacter);
                    for (int i = 1; i <= characterList.size(); i++) {
                        Requests.Character characterSend = new Requests.Character();
                        PlayerCharacter character1 = characterList.get(i);
                        characterSend.name = character1.getName();
                        characterSend.id = i;
                        characterSend.position = character1.getPosition();
                        connection.sendTCP(characterSend);
                    }
                    numPlayers++;
                    if (numPlayers ==2) {

                        startPlay();

                    }
                }
                if (object instanceof Requests.MovState) {
                    PlayerCharacter playerCharacter = characterList.get(connection.getID());
                    Requests.MovState movState = (Requests.MovState) object;
                    playerCharacter.setMovState(movState.direction);
                }
                if (object instanceof Requests.InitiateAction) {
                    Requests.InitiateAction initiateAction = (Requests.InitiateAction) object;
                    PlayerCharacter pc = characterList.get(initiateAction.pcid);
                    Action action = thingMap.get(initiateAction.thingId).getActions().get(initiateAction.actionId);
                    ActionEndListener listener = new ActionEndListener() {
                        @Override
                        public void actionEnded(int actionId) {
                            Requests.ActionEnded actionEnded = new Requests.ActionEnded();
                            actionEnded.actionId = actionId;
                            actionEnded.pcId = initiateAction.pcid;
                            server.sendToAllTCP(actionEnded);
                        }
                    };
                    pc.initiateAction(action, listener);
                }
                if (object instanceof Requests.IdentifierMessage) {

                    Requests.IdentifierMessage iMessage = (Requests.IdentifierMessage) object;
                    Requests.IdentifierResponse response;
                    //logger.info("The message client {} sent was Identifier Message {}", connection.getID(), iMessage.id);
                    if (iMessage instanceof IdentifierMessage.GetSpoons) {
                        PlayerCharacter playerCharacter = characterList.get(connection.getID());
                        int spoons = playerCharacter.getSpoons();
                        response =  new IdentifierResponse.SendInt(spoons);

                    }

                    else if (iMessage instanceof IdentifierMessage.GetName) {
                        IdentifierMessage.GetName getName = (IdentifierMessage.GetName) iMessage;
                        Thing thing = thingMap.get(getName.thingId);
                        String name = thing.getName();
                        response = new IdentifierResponse.SendString(name);
                    }

                    else if (iMessage instanceof IdentifierMessage.GetId) {
                        response = new IdentifierResponse.SendInt(connection.getID());
                    }
                    else if (iMessage instanceof  IdentifierMessage.GetPosition) {
                        IdentifierMessage.GetPosition getPosition = (IdentifierMessage.GetPosition) object;
                        Thing thing = thingMap.get(getPosition.thingId);
                        Position position = thing.getPosition();
                        response = new IdentifierResponse.SendPosition(position);
                    }
                    else if (iMessage instanceof IdentifierMessage.GetLocation) {
                        IdentifierMessage.GetLocation getLocation = (IdentifierMessage.GetLocation) object;
                        Thing thing = thingMap.get(getLocation.thingId);
                        logger.info("I have successfully retrieved this thing and its id is " + thing.getId());
                        int locId = thing.getLocation().getLocationID();
                        response = new IdentifierResponse.SendLocation(locId);
                    }
                    else if (iMessage instanceof IdentifierMessage.GetThings) {
                        IdentifierMessage.GetThings getThings = (IdentifierMessage.GetThings) object;
                        Location location = locationMap.get(getThings.locId);
                        List<Thing> thingList = location.getThings();
                        List<Integer> idList = new ArrayList<>();
                        for (Thing thing: thingList) {
                            idList.add(thing.getId());
                        }
                        response = new IdentifierResponse.SendIdList(idList);
                    }
                    else if (iMessage instanceof IdentifierMessage.GetEntrances) {
                        IdentifierMessage.GetEntrances getEntrances = (IdentifierMessage.GetEntrances) object;
                        Location location = locationMap.get(getEntrances.locId);
                        List<Position> entrances = location.getEntrances();
                        response = new IdentifierResponse.SendPosList(entrances);

                    }
                    else if (iMessage instanceof IdentifierMessage.GetThingsWorld) {
                        Map<Integer, Thing> thingMap = world.getThings();
                        List<Integer> idList = new ArrayList<>();
                        for (Thing thing: thingMap.values()) {
                            idList.add(thing.getId());
                        }
                        response = new IdentifierResponse.SendIdList(idList);
                    }
                    else if (iMessage instanceof IdentifierMessage.ExamineThing) {
                        IdentifierMessage.ExamineThing examineThing = (IdentifierMessage.ExamineThing) iMessage;
                        Map<Integer, Action> actionMap = thingMap.get(examineThing.thingId).examine(characterList.get(examineThing.pcId));
                        List<Integer> idList = new ArrayList<>();
                        List<String> nameList = new ArrayList<>();
                        for (Action action: actionMap.values()) {
                            idList.add(action.getActionId());
                            nameList.add(action.getActionName());
                        }
                        response = new IdentifierResponse.SendIntAndString(idList, nameList);
                    }
                    else if (iMessage instanceof IdentifierMessage.GetDuration) {
                        IdentifierMessage.GetDuration getDuration = (IdentifierMessage.GetDuration) iMessage;
                        Thing thing = thingMap.get(getDuration.thingId);
                        Map<Integer, Action> actionMap = thing.getActions();
                        int duration = actionMap.get(getDuration.actionId).getActionDuration();
                        response = new IdentifierResponse.SendInt(duration);
                    }
                    else if (iMessage instanceof IdentifierMessage.GetSpoonCost) {
                        IdentifierMessage.GetSpoonCost getSpoonCost = (IdentifierMessage.GetSpoonCost) iMessage;
                        Thing thing = thingMap.get(getSpoonCost.thingId);
                        Map<Integer, Action> actionMap = thing.getActions();
                        int cost = actionMap.get(getSpoonCost.actionId).getActionSpoonCost();
                        response = new IdentifierResponse.SendInt(cost);
                    }
                    else if (iMessage instanceof IdentifierMessage.CanPerformAction) {
                        IdentifierMessage.CanPerformAction canPerformAction = (IdentifierMessage.CanPerformAction) iMessage;
                        Thing thing = thingMap.get(canPerformAction.thingId);
                        Map<Integer, Action> actionMap = thing.getActions();
                        boolean sendMe = actionMap.get(canPerformAction.actionId).canPerformAction();
                        response = new IdentifierResponse.SendBool(sendMe);
                    }
//                    else if (iMessage instanceof IdentifierMessage.GetStartMsg) {
//                        IdentifierMessage.GetStartMsg getStartMsg = (IdentifierMessage.GetStartMsg) iMessage;
//                        Thing thing = thingMap.get(getStartMsg.actionId);
//                        List<String> strings = thing.getActions().get(getStartMsg.actionId).startActionMessage();
//                        response = new IdentifierResponse.SendStringList(strings);
//                    }
                    else if (iMessage instanceof IdentifierMessage.GetEndMsg) {
                        IdentifierMessage.GetEndMsg getEndMsg = (IdentifierMessage.GetEndMsg) iMessage;
                        Thing thing = thingMap.get(getEndMsg.thingId);
                        List<String> strings = thing.getActions().get(getEndMsg.actionId).endActionMessage();
                        response = new IdentifierResponse.SendStringList(strings);
                    }
                    else if (iMessage instanceof IdentifierMessage.GetDescription) {
                        IdentifierMessage.GetDescription getDescription = (IdentifierMessage.GetDescription) iMessage;
                        Thing thing = thingMap.get(getDescription.thingId);
                        String description = thing.getActions().get(getDescription.actionId).getActionDescription();
                        response = new IdentifierResponse.SendString(description);
                    }
                    else if (iMessage instanceof IdentifierMessage.HasDescription) {
                        IdentifierMessage.HasDescription hasDescription = (IdentifierMessage.HasDescription) iMessage;
                        Thing thing = thingMap.get(hasDescription.thingId);
                        boolean hasDesc = thing.getActions().get(hasDescription.actionId).hasDescription();
                        response = new IdentifierResponse.SendBool(hasDesc);
                    }
                    else if (iMessage instanceof IdentifierMessage.GetMovState) {
                        IdentifierMessage.GetMovState getMovState = (IdentifierMessage.GetMovState) iMessage;
                        Direction direction = characterList.get(getMovState.pcId).getMovState();
                        response = new IdentifierResponse.SendDirection(direction);
                    }
                    else {
                        throw new IllegalStateException("Received unrecognised identifier message : " + iMessage.getClass().getName());
                    }
                    response.id = iMessage.id;
                    connection.sendTCP(response);
                    //logger.info("I am sending idResponse {}", response.id);
                }


            }
        });

    }

    public void playGame() {
        while (state != GameState.STOPPING) {

            try {
                Thread.sleep(30);
            }
            catch (InterruptedException e) {

            }
            if (state == GameState.PLAYING) {

                world.timestep();
                for (TimeListener listener: timeListeners) {
                    listener.timeStep();
                }
            }
        }
    }



    @Override
    public void stopGame() {
        state = GameState.STOPPING;
        server.stop();
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public void startPlay() {
        System.out.println("Ready to go!");
        state = GameState.PLAYING;
        Requests.StartPlay startPlay = new Requests.StartPlay();
        startPlay.numPlayers = numPlayers;
        server.sendToAllTCP(startPlay);
    }

    @Override
    public void addTimeListener(TimeListener timeListener) {
        timeListeners.add(timeListener);
    }

    @Override
    public void removeTimeListener(TimeListener timeListener) {
        timeListeners.remove(timeListener);
    }

    @Override
    public void addThingtoMap(Thing thing) {
        thingMap.put(thing.getId(), thing);
    }

    @Override
    public void pause() {
        state = GameState.PAUSED;
    }

    @Override
    public void resume() {
        state = GameState.PLAYING;
    }
}