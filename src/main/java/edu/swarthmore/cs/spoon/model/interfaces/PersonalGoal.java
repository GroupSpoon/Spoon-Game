package edu.swarthmore.cs.spoon.model.interfaces;

import java.util.List;

/**
 * personal goals are to be completed by specified players and people they have asked for help from
 */
public interface PersonalGoal extends Goal{
    /**
     * checks to see if doing an action completes this goal
     * @param action the action in question
     * @return whether goal is complete
     */
    public boolean completesGoal(Action action);

    /**
     * gets the list of people who are allowed to complete this goal - the player it wasassigned to and everyone they ask for help
     * @return list of players
     */
    public List<PlayerCharacter> canComplete();

    /**
     * adds a player to the list of people allowed to complete a goal
     * @param playerCharacter the player to add
     */
    public void addPlayer(PlayerCharacter playerCharacter);

}
