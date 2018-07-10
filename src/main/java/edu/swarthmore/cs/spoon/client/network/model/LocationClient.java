package edu.swarthmore.cs.spoon.client.network.model;

import edu.swarthmore.cs.spoon.client.network.interfaces.GameClient;
import edu.swarthmore.cs.spoon.common.NotYetImplementedException;
import edu.swarthmore.cs.spoon.common.messages.IdentifierMessage;
import edu.swarthmore.cs.spoon.common.messages.IdentifierResponse;
import edu.swarthmore.cs.spoon.common.messages.Requests;
import edu.swarthmore.cs.spoon.model.interfaces.LocCreatedThingListener;
import edu.swarthmore.cs.spoon.model.interfaces.Location;
import edu.swarthmore.cs.spoon.model.interfaces.Position;
import edu.swarthmore.cs.spoon.model.interfaces.Thing;

import java.util.ArrayList;
import java.util.List;

public class LocationClient implements Location {
    private int id;
    private int height;
    private int width;
    private String name;
    private GameClient client;
    private List<LocCreatedThingListener> thingListeners = new ArrayList<>();

    public LocationClient(String name, int id, int height, int width, GameClient client) {
        this.id = id;
        this.height = height;
        this.width = width;
        this.name = name;
        this.client = client;

    }

    @Override
    public void setLocationName(String string) {
        throw new NotYetImplementedException();
    }

    @Override
    public void addLocCreatedThingListener(LocCreatedThingListener locCreatedThingListener) {
        thingListeners.add(locCreatedThingListener);
    }

    @Override
    public Boolean removeLocCreatedThingListener(LocCreatedThingListener locCreatedThingListener) {
        return thingListeners.remove(locCreatedThingListener);
    }

    @Override
    public String getLocationName() {
        return name;
    }

    @Override
    public int getLocationID() {
        return id;
    }


    @Override
    public int getLocationHeight() {
        return height;
    }

    @Override
    public int getLocationWidth() {
        return width;
    }

    @Override
    public Boolean addThing(Thing thing) {
        throw new NotYetImplementedException();
    }

    @Override
    public Boolean removeThing(Thing thing) {
        throw new NotYetImplementedException();
    }

    @Override
    public List<Thing> getThings() {
        IdentifierMessage.GetThings getThings = new IdentifierMessage.GetThings();
        getThings.locId = id;
        Requests.IdentifierResponse response = client.sendMessageandGetReply(getThings);
        IdentifierResponse.SendIdList idList = (IdentifierResponse.SendIdList) response;
        List<Thing> things = new ArrayList<>();
        for (int id: idList.idList) {
            things.add(client.getWorld().getThings().get(id));
        }
        return things;
    }

    @Override
    public List<Position> getEntrances() {
        IdentifierMessage.GetEntrances getEntrances = new IdentifierMessage.GetEntrances();
        getEntrances.locId = id;
        Requests.IdentifierResponse response = client.sendMessageandGetReply(getEntrances);
        IdentifierResponse.SendPosList posMessage = (IdentifierResponse.SendPosList) response;
        return posMessage.posList;
    }

    @Override
    public Boolean isValidPosition(Position position) {
        throw new NotYetImplementedException();
    }

    @Override
    public Boolean isColliding(Thing collider, Position pos) {
        throw new NotYetImplementedException();
    }

    @Override
    public Boolean locationActive() {
        throw new NotYetImplementedException();
    }

    @Override
    public List<Location> accessibleLocations() {

        throw new NotYetImplementedException();
    }


}
