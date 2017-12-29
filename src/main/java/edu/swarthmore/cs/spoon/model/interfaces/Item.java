package edu.swarthmore.cs.spoon.model.interfaces;

/**
 * Any Thing that it is possible for a player to pick up and add to their inventory
 */
public interface Item extends Thing{

    /**
     * Remove this Item from its current Location and Position and add it to the player's inventory
     */
    public void pickUp();

    /**
     * Give this item to another player character
     * @param recipient - the player charater to whom this Item is to be given
     */
    public void give(PlayerCharacter recipient);

    /**
     * Remove this Item from the player's inventory and add it to whatever Position in whatever Location it was dropped at
     */
    public void drop();


}
