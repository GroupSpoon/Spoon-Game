package edu.swarthmore.cs.spoon.model.interfaces;
/**
 * A friendly plant that a player can adopt and raise in their house
 */
public interface Plant extends Thing {
    /**
     * Getter for this plant's species
     * @return a string describing the plant's species
     */
    public String getSpecies();


}
