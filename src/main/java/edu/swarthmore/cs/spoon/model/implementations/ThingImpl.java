package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.model.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThingImpl implements Thing {
    protected String name;
    protected int thingId/* = IDManager.assignId()*/;
    protected Position thingCurrPos = null;
    protected Location thingCurrLoc;
    protected Map<Integer, Action> thingActions = new HashMap<>();
    private Map<Integer, Action> allThingActions = new HashMap<>();
    protected List<ThingEnteredLocListener> thingEnteredLocListenerList = new ArrayList<>();
    protected List<ActionAddedListener> actionAddedListenerList = new ArrayList<>();
    protected List<ActionRemovedListener> actionRemovedListenerList = new ArrayList<>();
    protected ThingType type;
    protected List<MovementListener> movementListenerList = new ArrayList<>();
    protected boolean isInUse = false;
    private boolean isSolid = true;
    private Map<Integer, Action> thingInUseActions = new HashMap<>();

/*
    public ThingImpl(String name, Location loc, Position pos){
        this.name = name;
        this.thingCurrLoc = loc;
        loc.addThing(this);
        this.thingCurrPos = pos;

    }
*/
//constructor just for things that are not pcs
    public ThingImpl(String name, Location loc, Position pos, ThingListenersHolder holder, ThingType type){
        this.name = name;
        this.thingId = IDManager.assignId();
        this.thingCurrLoc = loc;
        loc.addThing(this);
        this.thingCurrPos = pos;
        this.type = type;

        //Make the action that will be available if the thing is in use
        List<String> inUseActionMsg = new ArrayList<>();
        inUseActionMsg.add("You should wait your turn!");
        Action inUseAction = new ActionImpl("Try to use", this, 0, inUseActionMsg, 0);
        this.thingInUseActions.put(inUseAction.getActionId(), inUseAction);
        this.allThingActions.put(inUseAction.getActionId(), inUseAction);

        for(NewThingListener listener : holder.getAllListeners()){
            listener.newThingCreated(this.thingId, this);
        }

    }

    @Override
    public ThingType getThingType() {
        return type;
    }


    @Override
    public Map<Integer, Action> examine(PlayerCharacter examiner)
    {
        if(isInUse){
         return thingInUseActions;
        }
        return thingActions;
    }

    @Override
    public String getName() {
        return name;
    }



    @Override
    public Location getLocation() {
        return this.thingCurrLoc;
    }

    @Override
    public Position getPosition() {
        return this.thingCurrPos;
    }

    @Override
    public Boolean setLocation(Location newLocation) {
        if(this.thingCurrLoc != null){//If the curr loc is not null, then it already has a location
        if (this.thingCurrLoc == newLocation) {//Is that location the location we're trying to give it?
            return false; //We are trying to make the current location be the new location. How silly
        }
        //THe thing has a location already, and it isn't the location we're trying to give it
            this.thingCurrLoc.removeThing(this); //So we remove it from that location
        }
        //Okay, either it never had a location to begin with or it doesn't anymore
        //either way, we need to put it into newLocation
        this.thingCurrLoc = newLocation;
        newLocation.addThing(this); //Now go do it from the other side. Tell the location to add the thing
        for (ThingEnteredLocListener thingELocListener : this.thingEnteredLocListenerList) {
            //Now notify the listeners.
            //TODO: figure out whether we still need this
            thingELocListener.ThingEntered(this.thingCurrLoc.getLocationID(), this.thingId, this);
        }
        return true;
    }

    @Override
    public Boolean setPosition(Position newPosition) {
        if (this.thingCurrLoc != null) {
            /*

            TODO: figure out why this was like this? It shouldn't be like this
            this.thingCurrPos = newPosition; //Update the thing's own position field
            for (MovementListener movListener : this.movementListenerList) {
                movListener.thingMoved(this.thingId, newPosition);
            }*/
            //return false;//The thing has no location! How are we supposed to give it a position?
         if (this.thingCurrLoc.isValidPosition(newPosition)) {//Check that the new position would be within the bounds of the current location
            this.thingCurrPos = newPosition; //Update the thing's own position field
            for (MovementListener movListener : this.movementListenerList) {
                movListener.thingMoved(this.thingId, newPosition);
            }
            return true;
        }
    }
        return false;
    }


    @Override
    public void addAction(Action action) {
        this.thingActions.put(action.getActionId(), action);
        this.allThingActions.put(action.getActionId(), action);

    }

    @Override
    public Boolean removeAction(Action action) {
        Boolean toReturn = this.allThingActions.remove(action.getActionId(), action);

        return toReturn;

    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public void setPrice(int price) {

    }

    @Override
    public Boolean isSolid() {
        return isSolid;
    }

    @Override
    public void setSolidity(Boolean solidity) {
        isSolid = solidity;
    }

    @Override
    public int getId() {
        return thingId;
    }

    @Override
    public void addMovementListener(MovementListener movementListener) {
        this.movementListenerList.add(movementListener);
    }

    @Override
    public Boolean removeMovementListener(MovementListener movementListener) {
        return this.movementListenerList.remove(movementListener);
    }

    @Override
    public void addThingEnteredLocListener(ThingEnteredLocListener thingEnteredLocListener) {
        this.thingEnteredLocListenerList.add(thingEnteredLocListener);
    }

    @Override
    public Boolean removeThingEnteredLocListener(ThingEnteredLocListener thingEnteredLocListener) {
        return this.thingEnteredLocListenerList.remove(thingEnteredLocListener);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThingImpl thing = (ThingImpl) o;

        return thingId == thing.thingId;
    }

    @Override
    public int hashCode() {
        return thingId;
    }

    @Override
    public void addActionAddedListener(ActionAddedListener listener) {
        this.actionAddedListenerList.add(listener);
    }

    @Override
    public Boolean removeActionAddedListener(ActionAddedListener listener) {
        return actionAddedListenerList.remove(listener);
    }

    @Override
    public void addActionRemovedListener(ActionRemovedListener listener) {
        this.actionRemovedListenerList.add(listener);
    }

    @Override
    public Boolean removeActionRemovedListener(ActionRemovedListener listener) {
        return this.actionRemovedListenerList.remove(listener);
    }

    @Override
    public Map<Integer, Action> getActions() {
        return allThingActions;
    }

    @Override
    public boolean isInUse() {
        return this.isInUse;
    }

    @Override
    public void setInUse(boolean isInUse) {
        this.isInUse = isInUse;
    }
}
