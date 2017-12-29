package edu.swarthmore.cs.spoon.model.interfaces;

/**
 * campaign goals are for any player to finish over the course of the game. They must be finished by the end of the game. All campaign goals need an item to be completed
 */
public interface CampaignGoal extends Goal{
    /**
     * checks if this ite, completes this goal
     * @param item the item under consideration
     * @return whether the goal has been completed
     */
    public boolean completesGoal(Item item);
}
