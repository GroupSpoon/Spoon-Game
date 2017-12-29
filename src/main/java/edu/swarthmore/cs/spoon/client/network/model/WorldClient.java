package edu.swarthmore.cs.spoon.client.network.model;

import edu.swarthmore.cs.spoon.client.network.GameClientImpl;
import edu.swarthmore.cs.spoon.common.NotYetImplementedException;
import edu.swarthmore.cs.spoon.common.messages.IdentifierMessage;
import edu.swarthmore.cs.spoon.common.messages.IdentifierResponse;
import edu.swarthmore.cs.spoon.common.messages.Requests;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import edu.swarthmore.cs.spoon.client.network.interfaces.GameClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldClient implements World {
    GameClient client;
    private final Logger logger = LoggerFactory.getLogger(GameClientImpl.class);
    public int id;
    List<PlayerCharacter> characters = new ArrayList<>();
    Map<Integer, PlayerCharacter> characterMap = new HashMap<>();
    Map<Integer, Thing> thingMap = new HashMap<>();
    private Map<Integer, Location> locList = new HashMap<>();
    List<NewLocListener> locListeners = new ArrayList<>();

    public WorldClient(GameClient client) {
        System.out.println("Creating a client world!");
        this.client = client;
        this.id = id;


    }

    @Override
    public PlayerCharacter createCharacter(String name, int id) {

        IdentifierMessage.GetLocation getLocation = new IdentifierMessage.GetLocation();
        getLocation.thingId = id;
        IdentifierResponse.SendLocation identifierResponse = (IdentifierResponse.SendLocation) client.sendMessageandGetReply(getLocation);
        Location location = locList.get(identifierResponse.locationId);
        IdentifierMessage.GetPosition getPosition = new IdentifierMessage.GetPosition();
        getPosition.thingId = id;
        IdentifierResponse.SendPosition position = (IdentifierResponse.SendPosition) client.sendMessageandGetReply(getPosition);
        PlayerCharacter character = new PlayerCharacterClient(client, name, id, location, position.position);
        characters.add(character);
        characterMap.put(id, character);
        thingMap.put(id, character);
        return character;
    }


    public Thing createThing(String name, int id, Location loc, Position pos, ThingType type) {
        logger.info("World is about to create " + name);
        Thing thing = new ThingClient(id, pos, type, client);
        thingMap.put(id, thing);
        logger.info("World now knows about " + name);
        return null;
    }

    @Override
    public Map<Integer, PlayerCharacter> getCharacters() {
        System.out.println("We're in get Characters");
        return characterMap;
    }

    @Override
    public PlayerCharacter getCharacter(int id) {
        return characterMap.get(id);
    }

    @Override
    public Map<Integer, Thing> getThings() {
        return thingMap;
    }


    public Location createNewLoc(String locName, int id, int height, int width) {
        Location loc = new LocationClient(locName, id, height, width, client);
        locList.put(id, loc);
        for (NewLocListener listener : locListeners) {
            listener.newLocCreated(id);
        }
        return loc;
    }

    @Override
    public void addGoal(CampaignGoal goal) {

    }

    @Override
    public void startMaze() {

    }

    @Override
    public void startDay() {

    }

    @Override
    public void startNight() {

    }

    @Override
    public Map<Integer, Location> getLocationMap() {
        return locList;
    }

    @Override
    public void addNewLocListener(NewLocListener newLocListener) {
        locListeners.add(newLocListener);
    }

    @Override
    public Boolean removeNewLocListener(NewLocListener newLocListener) {
        return locListeners.remove(newLocListener);
    }

    @Override
    public void addNewThingListener(NewThingListener newThingListener) {

    }

    @Override
    public Boolean removeNewThingListener(NewThingListener newThingListener) {
        return null;
    }

    @Override
    public void addNewPCListener(NewPCListener newPCListener) {

    }

    @Override
    public Boolean removeNewPCListener(NewPCListener newPCListener) {
        throw new NotYetImplementedException();
    }

    @Override
    public void timestep() {
        throw new NotYetImplementedException();
    }

}
