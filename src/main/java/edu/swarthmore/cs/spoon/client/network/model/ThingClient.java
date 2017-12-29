package edu.swarthmore.cs.spoon.client.network.model;

import edu.swarthmore.cs.spoon.client.network.GameClientImpl;
import edu.swarthmore.cs.spoon.client.network.interfaces.GameClient;
import edu.swarthmore.cs.spoon.common.NotYetImplementedException;
import edu.swarthmore.cs.spoon.common.messages.IdentifierMessage;
import edu.swarthmore.cs.spoon.common.messages.IdentifierResponse;
import edu.swarthmore.cs.spoon.common.messages.Requests;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThingClient implements Thing{
    private final Logger logger = LoggerFactory.getLogger(GameClientImpl.class);
    protected int id;
    protected Position position;
    protected GameClient client;
    protected List<ThingEnteredLocListener> locListeners = new ArrayList<>();
    protected List<MovementListener> movementListeners = new ArrayList<>();
    protected List<ActionAddedListener> actAddedListeners = new ArrayList<>();
    protected List<ActionRemovedListener> actRemovedListeners = new ArrayList<>();
    private ThingType type;

    public ThingClient(int id, Position pos, ThingType type, GameClient client) {
        logger.info("look out there's about to be a thing");
        this.id = id;
        this.type = type;
        this.client = client;
        this.position = pos;
        //setLocation(getLocation());
        client.addMovementListener(new MovementListener() {
            @Override
            public void thingMoved(int thingId, Position pos) {
                if (thingId == id) {
                    position = pos;
                    for (MovementListener listener:movementListeners) {
                        listener.thingMoved(id, position);
                    }
                }
            }
        });
        client.addLocationListener(new ThingEnteredLocListener() {
            @Override
            public void ThingEntered(int locId, int thingId, Thing thing) {
                for (ThingEnteredLocListener locListener: locListeners) {
                    locListener.ThingEntered(locId, thingId, thing);
                }
            }
        });
        logger.info("Hello, I am " + this.getName() + " and I exist now");
    }

    @Override
    public ThingType getThingType() {
        return type;
    }


    @Override
    public Map<Integer, Action> examine(PlayerCharacter examiner) {
        IdentifierMessage.ExamineThing examineThing = new IdentifierMessage.ExamineThing();
        examineThing.pcId = examiner.getId();
        examineThing.thingId = this.id;
        Requests.IdentifierResponse response = client.sendMessageandGetReply(examineThing);
        IdentifierResponse.SendIntAndString actionIds = (IdentifierResponse.SendIntAndString) response;
        Map<Integer, Action> actionMap = new HashMap<>();
        for (int i = 0; i < actionIds.ints.size(); i++) {
            actionMap.put(i, new ActionClient(actionIds.ints.get(i), actionIds.strings.get(i), this, client));
        }
        return actionMap;
    }

    @Override
    public String getName() {
        IdentifierMessage.GetName getName = new IdentifierMessage.GetName();
        getName.thingId = id;
        IdentifierResponse.SendString response = (IdentifierResponse.SendString) client.sendMessageandGetReply(getName);
        return response.stringToSend;
    }

    @Override
    public Location getLocation() {
        IdentifierMessage.GetLocation getLocation = new IdentifierMessage.GetLocation();
        getLocation.thingId = id;
        logger.info("I want my location and I think my id is " + id);
        Requests.IdentifierResponse response = client.sendMessageandGetReply(getLocation);
        IdentifierResponse.SendLocation loc = (IdentifierResponse.SendLocation) response;
        logger.info("I have received my location from ");
        Location location = client.getWorld().getLocationMap().get(loc.locationId);
        return location;
    }

    @Override
    public Position getPosition() {
        IdentifierMessage.GetPosition getPosition = new IdentifierMessage.GetPosition();
        getPosition.thingId = id;
        Requests.IdentifierResponse response = client.sendMessageandGetReply(getPosition);
        IdentifierResponse.SendPosition position = (IdentifierResponse.SendPosition) response;
        return position.position;
    }

    public Boolean setLocation(Location newLocation) {
        throw new NotYetImplementedException();

    }

    public Boolean setPosition(Position newPosition) { throw new NotYetImplementedException();
    }

    @Override
    public Map<Integer, Action> getActions() {
        return null;
    }

    @Override
    public boolean isInUse() {
        return false;
    }

    @Override
    public void setInUse(boolean isInUse) {
        throw new NotYetImplementedException();
    }

    @Override
    public void addAction(Action action) {
        throw new NotYetImplementedException();
    }

    @Override
    public Boolean removeAction(Action action) {
        throw new NotYetImplementedException();
    }

    @Override
    public int getPrice() {
        IdentifierMessage.GetPrice getPrice = new IdentifierMessage.GetPrice();
        getPrice.thingId = id;
        IdentifierResponse.SendInt response = (IdentifierResponse.SendInt) client.sendMessageandGetReply(getPrice);
        return response.intToSend;
    }

    @Override
    public void setPrice(int price) {
        throw new NotYetImplementedException();
    }

    @Override
    public Boolean isSolid() {
        IdentifierMessage.IsSolid isSolid = new IdentifierMessage.IsSolid();
        isSolid.thingId = id;
        IdentifierResponse.SendBool response = (IdentifierResponse.SendBool) client.sendMessageandGetReply(isSolid);
        return response.boolToSend;
    }

    @Override
    public void setSolidity(Boolean solidity) {
    throw new NotYetImplementedException();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void addMovementListener(MovementListener movementListener) {
        movementListeners.add(movementListener);

    }

    @Override
    public Boolean removeMovementListener(MovementListener movementListener) {
        return movementListeners.remove(movementListener);
    }

    @Override
    public void addThingEnteredLocListener(ThingEnteredLocListener thingEnteredLocListener) {
        locListeners.add(thingEnteredLocListener);

    }

    @Override
    public Boolean removeThingEnteredLocListener(ThingEnteredLocListener thingEnteredLocListener) {
        return locListeners.remove(thingEnteredLocListener);
    }


    @Override
    public void addActionAddedListener(ActionAddedListener listener) {
        actAddedListeners.add(listener);
    }

    @Override
    public Boolean removeActionAddedListener(ActionAddedListener listener) {
        return actAddedListeners.remove(listener);
    }

    @Override
    public void addActionRemovedListener(ActionRemovedListener listener) {
        actRemovedListeners.add(listener);
    }

    @Override
    public Boolean removeActionRemovedListener(ActionRemovedListener listener) {
        return actRemovedListeners.remove(listener);
    }
}
