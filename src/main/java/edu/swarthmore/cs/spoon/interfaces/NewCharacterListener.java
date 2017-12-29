package edu.swarthmore.cs.spoon.interfaces;

import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;

/**
 * An interface for a listener that runs upon the creation of a new character
 */
public interface NewCharacterListener {
    /**
     * A method for a new character assumed to be in the same location as the player
     * #TODO: This method is made with the assumption that there is only one character in a Location
     * @param player
     */
    void NewCharacterInLocation(PlayerCharacter player);
}
