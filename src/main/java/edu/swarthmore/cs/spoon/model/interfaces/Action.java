package edu.swarthmore.cs.spoon.model.interfaces;


import java.util.List;

/**
 * An Action refers to player action that costs spoons, affecting the Spoon Allowance Point System within the game.
 *
 */

public interface Action {


    /**
     * Each Action has a name to identify the Action.
     * @return a string indicating the name of the action.
     */
    String getActionName();

    /**
     * Getter for the duration of the action. How many timesteps it takes to finish
     * @return the duration
     */
    int getActionDuration();

    /**
     * An action has an associated spoon cost.
     * @return an integer indicating the spoon cost for the action
     */
    int getActionSpoonCost();

    /**
     * Some Actions may, as a consequence, directly affect player stats.
     * @return a list of strings, each string indicating a player stat that taking the action successfully can affect the status listed by each string. OR null if there is no status effect
     */
    //DEPRECATED
    List<String> getActionStatusEffects();


    /**
     * Indicates whether or not one can perform the action they're trying to do.
     * @return true if the player can perform the action, and return false if they can't, or if there is no actor.
     */
    boolean canPerformAction();


    /**
     * Sets the spoon cost of the action.
     * @param num an integer representing the spoon cost. Returns
     */
    void setSpoonCost(int num);


    /**
     * A function that indicates whether or not the Action that the player is trying to do is something that they can receive help on from another player
     * @return true, if the player can call for help for the Action, OR false if the player cannot call for help for the Action.
     */
    boolean canGetHelpForAction();


    /**
     * gives the strings of text for the end of the action  //and provides all associated dialogue with ending the Action.
     * This will now be the only message that the action will display
     * @return a list of strings indicating action's ending dialogue and consequences, OR null if there is no dialogue
     */
    List<String> endActionMessage();

    /**
     * gets actions description
     * @return returns description, or an empty string if there is no description
     */
    String getActionDescription();


    boolean hasDescription();
    /**
     * Makes the mechanical effects of the action happen.
     * This method will probably be different for every implementation of actions
     */
    void startAction();


    int getActionId();

    /**
     * Gets the Thing whose action this is.
     * @return
     */
    Thing getTarget();


    /**
     * Gets the PC? (could it be just a Thing?) who is taking this action
     * @return the PC if an actor has already been set, or null if there is no actor.
     */
    PlayerCharacter getActor();

    /**
     * Sets the actor for this action
     * NOTE: When an action is initiated by a pc, that method MUST CALL THIS METHOD
     *
     * @param actor the actor for this action
     */
    void setActor(PlayerCharacter actor);

    void addEndListener(ActionEndListener listener);
    void notifyListeners(boolean success);
    void removeEndListener(ActionEndListener listener);


}

/*
Ok heres how this needs to work:
You need a bunch of separate subparts:
    something to get the description of the action

    something to get the start message of the action

    something to get the end message of the action

    something to carry out the mechanical effect(s) of the action

  and then in playercharacter you have a method that takes in the action and strings all of those things
  together
  YAY
 */

