package edu.swarthmore.cs.spoon.model.interfaces;

/**
 * A friendly animal that a player can adopt and raise in their house
 */
public interface Pet extends Thing {
    /**
     * Getter for this pet's species
     * @return a string describing the pet's species
     */
    public String getSpecies();

}
