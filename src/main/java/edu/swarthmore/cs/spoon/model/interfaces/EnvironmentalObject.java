package edu.swarthmore.cs.spoon.model.interfaces;

/**
 * Any Thing that is an inanimate part of the environment and cannot be picked up by a player
 */
public interface EnvironmentalObject extends Thing{

    /**
     * Puts an Item inside this EnvironmentalObject
     * @return true if the item was placed successfully or false if this EnvironmentalObject already contains something else
     */
    public Boolean insertItem();

    /**
     * Removes the Item from this environmentalobject and returns it (or null if this environmentalObject does not contain an item
     * @return the Item that was removed, or null if the EnvironmentalObject did not contain an item
     */
    public Item removeItem();


    /**
     * checks whether this EnvironmentalObject contains an Item
     * @return true if there is an item, false if not
     */
    public Boolean search();


    /**
     * Getter for the item inside this EnvironmentalObject
     * @return the Item that this EnvironmentalObject contains, or null if it does not contain an item
     */
    public Item getItem();


}
