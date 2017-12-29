package edu.swarthmore.cs.spoon.client.gui;

import edu.swarthmore.cs.spoon.client.gui.interfaces.GUIPlayerSprites;
import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Collection;

/**
 * A basic PlayerSprite class, meant to use a rectangle object of 4 pixels to represent the player.
 * Use the rectangle for collision detection.
 */
public class PlayerSprite extends Rectangle implements GUIPlayerSprites{
    private String name;
    private int playerID;
    final int TILE = 32;
    final int SIZE = 4;
    private Image visibleSprite;

    public PlayerSprite(PlayerCharacter player, int id, Paint color) {
        super(32, 32, color); //Currently a player takes up 4 points
        this.name = player.getName();
        this.playerID = id;

    }

    public int getPlayerSize() {
        return SIZE;
    }
    
    public int getplayerID() {
        return this.playerID;
    }


    @Override
    public Image getDefaultImg() {
        return null;
    }

    @Override
    public void setDefaultImg() {

    }

    @Override
    public void setStillImg(String direction) {
        return;
    }

    @Override
    public Rectangle setCollisionRect() {
        return null;
    }

    @Override
    public Rectangle getCollisionRect() {
        return null;
    }

    @Override
    public Rectangle modifyCollisionRectSize(int width, int height) {
        return null;
    }

    @Override
    public Image getLastDirectionImg() {
        return null;
    }

    @Override
    public void setStandUpImg() {
        return;
    }

    @Override
    public void setStandDownImg() {
        return;
    }

    @Override
    public Image getStandLeftImg() {
        return null;
    }

    @Override
    public Image getStandRightImg() {
        return null;
    }

    @Override
    public void walkInDirectionCycle(String direction) {

    }

    @Override
    public void walkUPImageCycle() {

    }

    @Override
    public void walkDOWNImageCycle() {

    }

    @Override
    public void walkRIGHTImageCycle() {

    }

    @Override
    public void walkLEFTImageCycle() {

    }

    @Override
    public Collection<Image> getMoveRIGHTImages() {
        return null;
    }

    @Override
    public Collection<Image> getMoveLEFTImages() {
        return null;
    }

    @Override
    public Collection<Image> getMoveDOWNImages() {
        return null;
    }

    @Override
    public Collection<Image> getMoveUPImages() {
        return null;
    }
}
