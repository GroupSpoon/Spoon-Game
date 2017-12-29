package edu.swarthmore.cs.spoon.model.interfaces;

/**
 * A particular player's house. It is at this location (and this location alone) that the player can sleep (because their bed is located here), and therefore that this player can travel to the nighttime phase of the game
 * This is also where the player's plants and pets will live, if they have them.
 * The player will have to keep their house clean and in good working order.
 * Any other items or environmental objects that the player may be able to acquire (some of which might be extended features only) can be stored/displayed here
 */
public interface House extends Location{

    /**
     * Sets what PlayerCharacter this house belongs to
     * @param playerCharacter the PlayerCharacter this house should belong to
     */
    void setOwner(PlayerCharacter playerCharacter);

    /**
     * Getter for this House's owner
     * @return the owner of this House, or null if it has no owner (Which should never happen)
     */
    PlayerCharacter getOwner();

    /**
     * Adds a pet to this house
     * @param pet the pet to add to this house
     */
    void addPet(Pet pet);

    /**
     * Adds a plant to this house
     * @param plant the plant to add to this house
     */
    void addPlant(Plant plant);

    /**
     * Removes the specified pet from this house
     * @param pet the pet to try to remove
     * @return the pet that was removed or null if that pet was not there to begin with
     */
    Pet removePet(Pet pet);

    /**
     * Removes the specified plant from this house
     * @param plant the plant to try to remove
     * @return the plant that was removed or null if that plant was not there to begin with
     */
    Plant removePlant(Plant plant);

    /**
     * Getter for this house's pet
     * @return the pet that lives in this house, or null if this house has no pet
     */
    Pet getPet();

    /**
     * Getter for this house's plant
     * @return the plant that lives in this house, or null if this house has no plant
     */
    Plant getPlant();

    /**
     * Sets the cleanliness level for this house
     * @param cleanLevel an integer from 0 to 2 that describes how clean this house is, where 0 is completely clean and 2 is filthy
     */
    void setCleanliness(int cleanLevel);

    /**
     * Gets the cleanliness level for this house
     * @return an integer from 0 to 2 that describes how clean this house is, where 0 is completely clean and 2 is filthy
     */
    int getCleanliness();
}
