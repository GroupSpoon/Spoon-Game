package edu.swarthmore.cs.spoon.client.gui.interfaces;

import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;
import edu.swarthmore.cs.spoon.model.interfaces.Thing;
import javafx.scene.layout.Pane;

/**
 * A general interface for what the GUI needs to visually represent a location.
 * Implementations of this interface MUST EXTEND the Pane class
 */
public interface GUILocationPane{
    /**
     * Is able to render a Location graphically with rectangular PlayerSprites.
     */
    void GUILocation();


    /**
     * Function for setting up the backgroud of a location
     */
    void setBackgroundTiles();

    /**
     * Responsible for intially placing each player in the LocationPane.
     * @param player
     */
    void placePlayer(Thing player);

    /**
     * Responsible for moving the playersprite object associated with a player.
     * @param player
     */
    void relocateThing(Thing player);

    /**
     * Method responsible for creating a new PlayerSprite object and initially
     * Placing it on the board.
     * @param thing
     */
    void placeThing(Thing thing);

    /**
     * A method for getting and returning the client sprite
     * @return
     */
    GUIPlayerSprites getClientSprite();
}
