package edu.swarthmore.cs.spoon.client.gui.interfaces;

import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;
import edu.swarthmore.cs.spoon.model.interfaces.World;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.Collection;
import java.util.List;

public interface GUIWorldOrganizer {


    /**
     * Will initialize a world as follows, depending on the backend or server type.
     * @param world
     * @return A scene for its desired app to show, THE SCENE SHOULD HAVE ALL VISUAL ELEMENTS AND NODES
     */
    public Scene createLocationScene(World world, Pane rootPane);

    /**
     * Retrieve the characters from the world.
     * @return A Collection object containing each PlayerCharacter object from the world.
     */

    public Collection<PlayerCharacter> retrieveCharacters();
}
