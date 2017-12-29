package edu.swarthmore.cs.spoon.model.interfaces;


import java.util.List;

/**
 * This indicates the varying locations of the game.
 */
public interface Location {
    /**
     * A function for setting the name of the Location.
     * @param string, a string that represents the name of the Location.
     *
     */
    public void setLocationName(String string);


    /**
     * A function for getting the name of the location
     * @return a string indicating the name of the Location
     */
    public String getLocationName();

    /**
     * A function for getting the id of the location
     * @return an int, the id of the Location
     */
    public int getLocationID();

    /**
     * Getter for location height
     * @return the height
     */
    public int getLocationHeight();

    /**
     * Getter for location width
     * @return the width
     */
    public int getLocationWidth();


    /**
     * A function for adding a Thing to this Location
     * @param thing the thing to be added
     * @return false if the thing was already there, true otherwise
     */
    public Boolean addThing(Thing thing);

    /**
     * A function for removing a Thing from this location
     * @param thing the Thing to remove
     * @return true if successfully removed. False if something went wrong or it was never there to begin with
     */
    public Boolean removeThing(Thing thing);

    /**
     * Getter for the List of Things in this location
     * @return the list of Things in this location
     */
    public List<Thing> getThings();

    /**
     * Getter for the List of Positions that allow you to enter or exit this location
     * @return the list of entrances
     */
    public List<Position> getEntrances();


    /**
     * A function for determining whether a position can validly exist within the boundaries of this location
     * @param position the position to check for validity
     * @return true if the position is within the bounds of this location, false if not
     */
    public Boolean isValidPosition(Position position);

    /**
     * A function for determining whether a given position is overlapping with something else in the location
     * @param collider the thing that is moving, maybe into somethign else
     * @param pos the position to check
     * @return true if there is a collision, false if not
     */
    public Boolean isColliding(Thing collider, Position pos);


    /**
     * Method for adding a pLocCreatedThinglistener to this Location's pLocCreatedThinglistener list
     * @param locCreatedThingListener the listener to be added
     */
    public void addLocCreatedThingListener(LocCreatedThingListener locCreatedThingListener);

    /**
     * Method for removing a PLocCreatedThinglistener from this Location's LocCreatedThingListener list
     * @param locCreatedThingListener the listener to be removed
     * @return true if LocCreatedThingListener was in the list, false if it wasn't there to begin with
     */
    public Boolean removeLocCreatedThingListener(LocCreatedThingListener locCreatedThingListener);


    /**
     * A function for determining whether or not a player is currently viewing or present in a location
     * @return a boolean, true if a player is in the location, and false if a player is not in the location.
     */
    public Boolean locationActive();

    /**
     * A function for determining what Locations other than the current Location are accessible from the current Location
     * @return a list of Location objects that are accessible from the player's current Location
     */
    public List<Location> accessibleLocations();








}
