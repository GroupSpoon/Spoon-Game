package edu.swarthmore.cs.spoon.model.interfaces;

/** represents one player for one full game session
 */
public interface PlayerCharacter extends Thing {

    /**
     * gets name of character
     * @return string containing name
     */
    public String getName();


    /**
     * gets players spoons count
     * @return spoons player currently has
     */
    public int getSpoons();

    /**
     *adds the specified number of spoons to players spoon count
     * @param spoons number of spoons to add, can be negative
     */
    public void modifySpoons(int spoons);

    /**
     * getter forplayercharacter's money
     * @return the emount of money character has right now
     */
    public int getMoney();

    /**
     * adds the specified number of money to players money count
     * @param money amount of money to add, can be negative
     */
    public void modifyMoney(int money);

    /**
     * returns player's socialisation stat
     * @return sociall stat as integer
     */
    public int getSocial();

    /**
     * function to add or reduce a player's social stat
     * @param social the int to change it by - negative if stat needs to be reduced
     */
    public void modifySocial(int social);

    /**
     * stat to calculate how many spoons it costs to go World serverWorld = manager.getWorld();
     World clientWorld = client2.getWorld();from location to location
     * @return movement stat
     */
    public int getMovement();

    /**
     *
     * @param movement the amount to change the movement stat by
     */
    public void modifyMovement(int movement);

    /**
     * helpfulness measures how good player characters are at helping other player characters
     * @return their helpfulness stat
     */
    public int getHelpfulness();

    /**
     * increase or decrease helpfulness
     * @param helpfulness the amount to change helpfulness by
     */
    public void modifyHelpfulness(int helpfulness);


    /**
     * Method for setting the pc's movement state - setting what direction it is currently moving in (or, if it is not moving, setting that)
     * @param dir the direction of movement
     */
    public void setMovState(Direction dir);

    /**
     * getter for the movState
     * @return the current movState
     */
    public Direction getMovState();

    /**
     * Method to be called once every timestep. Attempts to move the pc in the direction specified by the current movement state. Notifies listeners if movement happens
     * TODO: add collision stuff
     */
    public void attemptStep();

    /**
     * One timestep. May or may not actually use this
     */
    public void timestep();

    /**
     * Method for adding a movement listener to this pc's MovementListener list
     * @param movementListener the listener to be added
     */
    public void addMovementListener(MovementListener movementListener);

    /**
     * Method for removing a movement listener from this pc's MovementListener list
     * @param movementListener the listener to be removed
     * @return true if movementListener was in the list, false if it wasn't there to begin with
     */
    public Boolean removeMovementListener(MovementListener movementListener);

    /**
     * Method for adding a movStateChangeListener to this pc's MovStateChangeListener list
     * @param movStateChangeListener the listener to be added
     */
    public void addMovStateChangeListener(MovStateChangeListener movStateChangeListener);

    /**
     * Method for removing a movStateChangeListener from this pc's MovStateChangeListener list
     * @param movStateChangeListener the listener to be removed
     * @return true if the listener was in the list, false if it wasn't there to begin with
     */
    public Boolean removeMovStateChangeListener(MovStateChangeListener movStateChangeListener);

    /**
     * Method for adding a pcenteredloc listener to this pc's pcenteredloc listener list
     * @param thingEnteredLocListener the listener to be added
     */
    public void addPcEnteredLocListener(ThingEnteredLocListener thingEnteredLocListener);

    /**
     * Method for removing a PCEnteredLoc listener from this pc's PcEnteredLocListener list
     * @param thingEnteredLocListener the listener to be removed
     * @return true if ThingEnteredLocListener was in the list, false if it wasn't there to begin with
     */
    public Boolean removePcEnteredLocListener(ThingEnteredLocListener thingEnteredLocListener);



    /**
     *  Position getter method
     * @return the players current location
     */
    public Position getPosition();

    /**
     * location getter method
     * @return player's current location
     */
    public Location getLocation();

    /**
     *
     * @return player's inventory
     */
    public Inventory getInventory();

    /**
     * Begins to carry out an action
     * sets the playerstate to actingstate
     * also MUST set the action's actor to the current player
     * TODO: make this set the action's spoon cost correctly too
     * @param action the action to initiate
     */
    public void initiateAction(Action action, ActionEndListener listener);

    public void attemptAct();

    public StateType getPlayerState();





}


