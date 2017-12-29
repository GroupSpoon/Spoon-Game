package edu.swarthmore.cs.spoon.client.gui.interfaces;

import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;
import javafx.scene.Scene;

public interface GUIKeyInput {
    /**
     * This method will handle the adding of the "up, down, eft, and right" keys onto various scenes
     * @param scene
     */
    public void addMovementKeysToScene(Scene scene, PlayerCharacter player);

    public void removeMovementKeysFromScene(Scene scene);

}
