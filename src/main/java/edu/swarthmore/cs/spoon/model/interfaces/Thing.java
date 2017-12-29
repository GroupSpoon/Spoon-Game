package edu.swarthmore.cs.spoon.model.interfaces;


import java.util.List;
import java.util.Map;

/**
 * Any entity in our game that can be represented as having physical existence and is not a location
 * This includes items the players can acquire, objects in the environment, pets, plants, and the player characters themselves
 */
public interface Thing {
    /**
     * Returnss which of the enumeration of ThingTypes thing Thing is
     */
    public ThingType getThingType();


    /**
     * Returns the map of possible actions that can be taken in relation to this Thing
     * @param examiner the playerCharacter examining this Thing
     * @return a map of Actions where every Action in the map is an Action that can be taken on/with/concerning the Thing
     */
    public Map<Integer, Action> examine(PlayerCharacter examiner);

    /**
     * Returns the name of this Thing as a String
     * @return the Name of this Thing
     */
    public String getName();

    /**
     * Returns the Location where this Thing exists
     * @return a Location, the one where this Thing is
     */
    public Location getLocation();

    /**
     * Returns the Position (x,y) of this Thing
     * @return the Position where this thing is located
     */
    public Position getPosition();

    /**
     * Moves this Thing to the Location passed in as a parameter
     * @param newLocation the Location that we want to set as this Thing's Location
     * @return True if the Location was changed successfully, False if newLocation was invalid
     */
    Boolean setLocation(Location newLocation);

    /**
     * Moves this Thing to the Position specified at the Thing's Location
     *
     * @param newPosition - the Position to change Thing's Position to
     * @return True if the Position was changed successfully, False if newPosition was invalid for this Thing's location
     */
    public Boolean setPosition(Position newPosition);

    /**
     * Adds the given action to this Thing's set of Actions
     * @param action the Action to be added
     */
    public void addAction(Action action);


    /**
     * Removes the specified Action from this Thing's set of Actions
     * @param action the Action to be removed
     * @return true if the action was removed successfully, false if not
     */
    public Boolean removeAction(Action action);

    /**
     * Returns an int indicating the price of this Item if it can be bought at a shop or null if it is a non-purchasable item
     * @return an int with the item's monetary value or null if it is not sellable
     */
    public int getPrice();

    /**
     * Sets the monetary value of this Thing
     * Note that not all Things are purchasable and thus this method will not be used by all kinds of Thing
     * @param price - the monetary value of this Thing
     */
    public void setPrice(int price);

    /**
     * Returns whether this Thing is Solid, i.e. whether or not the player can pass through/walk over it
     * @return true if this Thing is solid, and therefore cannot be walked through, false if it is not
     */
    public Boolean isSolid();

    /**
     * Sets this Thing's solidity value to the passed-in Boolean. True = solid, cannot be walked through. False = non-solid, can be walked through
     * @param solidity a Boolean describing what we want the Thing's solidity value to be set to.
     */
    public void setSolidity(Boolean solidity);

    public int getId();

    /**
     * Method for adding a movement listener to this thing's MovementListener list
     * @param movementListener the listener to be added
     */
    public void addMovementListener(MovementListener movementListener);

    /**
     * Method for removing a movement listener from this thing's MovementListener list
     * @param movementListener the listener to be removed
     * @return true if movementListener was in the list, false if it wasn't there to begin with
     */
    public Boolean removeMovementListener(MovementListener movementListener);


    /**
     * Method for adding a thingEnteredloc listener to this thing's thingEnteredloc listener list
     * @param thingEnteredLocListener the listener to be added
     */
    public void addThingEnteredLocListener(ThingEnteredLocListener thingEnteredLocListener);

    /**
     * Method for removing a thingEnteredLoc listener from this thing's ThingEnteredLocListener list
     * @param thingEnteredLocListener the listener to be removed
     * @return true if ThingEnteredLocListener was in the list, false if it wasn't there to begin with
     */
    public Boolean removeThingEnteredLocListener(ThingEnteredLocListener thingEnteredLocListener);

    public void addActionAddedListener(ActionAddedListener listener);

    public Boolean removeActionAddedListener(ActionAddedListener listener);

    public void addActionRemovedListener(ActionRemovedListener listener);

    public Boolean removeActionRemovedListener(ActionRemovedListener listener);

    /**
     * gets the map of ALL actions that this Thing has, including the one that you can only use if the thing is in use
     * @return
     */
    public Map<Integer, Action> getActions();

    /**
     * returns whether a playercharacter is currently performing an action associated with this Thing
     * @return true if one of the Thing's actions is currently being taken, false if not.
     */
    public boolean isInUse();

    /**
     * sets whether the Thing is currently being used
     * @param isInUse - true if the Thing should start being in use, false if it should stop being in use
     */
    public void setInUse(boolean isInUse);


}
