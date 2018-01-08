package edu.swarthmore.cs.spoon.model.interfaces;

import java.util.List;
import java.util.Map;

/**
 * handles things that affect all players uniformly; sets up the game and transitions from day to night
 */
public interface World {
    /**
     * creates a character for a player
     * @param name the name to give the character
     * @param id the id for the character
     * @return the character created
     */
    PlayerCharacter createCharacter(String name, int id);


    /**
     * Creates a Thing
     * @param name the name to give the thing
     * @param id the id of the thing TODO make this real
     * @param loc the location to put it
     * @param pos the position to put it
     * @param type the Type of Thing- PC, ITEM, ENVOBJ, PET, PLANT, or THING
     * @return the Thing created
     */
    //Thing createThing(String name, int id, Location loc, Position pos, ThingType type);


    /**
     * Getter for the map of ids to Things
     * @return
     */
    public Map<Integer, Thing> getThings();

    /**
     * Getter for the map of player characters
     * @return
     */
    public Map<Integer, PlayerCharacter> getCharacters();

    public PlayerCharacter getCharacter(int id);
    /**
     * Creates a new Location and returns it. Also notifies the NewLocListeners
     * @param locName the name for the location
     * @param id the id for the new location, supplied by the IDManager
     * @param height the height of the location
     * @param width the width of the location
     * @return the location
     */
    //public Location createNewLoc(String locName, int id, int height, int width);

    /**
     * adds a campaign goal to the game
     * @param goal the campaig goal to add
     */
    public void addGoal(CampaignGoal goal);

    /**
     * called MazeGame to start setting up the maze
     */
    public void startMaze();

    /**
     * changes game state to day, begins day timer
     */
    public void startDay();

    /**
     * transports all the players to the woods and starts the nighttime sequence
     */
    public void startNight();


    /**
     * getter for all the locations that exist in the world
     * @return a map of all the Location ID - location pairs that exist in the world
     */
    public Map<Integer, Location> getLocationMap();

    /**
     * Method for adding a NewLocListener to this world's NewLocListener list
     * @param newLocListener the listener to be added
     */
    public void addNewLocListener(NewLocListener newLocListener);

    /**
     * Method for removing a NewLocListener from this world's NewLocListener list
     * @param newLocListener the listener to be removed
     * @return true if NewLocListener was in the list, false if it wasn't there to begin with
     */
    public Boolean removeNewLocListener(NewLocListener newLocListener);

    /**
     * Method for adding a NewThingListener to this world's NewThingListener list
     * @param newThingListener the listener to be added
     */
    public void addNewThingListener(NewThingListener newThingListener);

    /**
     * Method for removing a NewThingListener from this world's NewThingListener list
     * @param newThingListener the listener to be removed
     * @return true if NewThingListener was in the list, false if it wasn't there to begin with
     */
    public Boolean removeNewThingListener(NewThingListener newThingListener);

    /**
     * Method for adding a NewPCListener to this world's NewPCListener list
     * @param newPCListener the listener to be added
     */
    public void addNewPCListener(NewPCListener newPCListener);

    /**
     * Method for removing a NewPCListener from this world's NewPCListener list
     * @param newPCListener the listener to be removed
     * @return true if NewPCListener was in the list, false if it wasn't there to begin with
     */
    public Boolean removeNewPCListener(NewPCListener newPCListener);



    /**
     * Advances everything in the game by one timestep
     */
    public void timestep();




}
