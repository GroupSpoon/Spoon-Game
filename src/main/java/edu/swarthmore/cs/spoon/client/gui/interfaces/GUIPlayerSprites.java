package edu.swarthmore.cs.spoon.client.gui.interfaces;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Collection;

public interface GUIPlayerSprites {

    public Image getDefaultImg();
    public void setDefaultImg();
    public void setStillImg(String direction);
    public Rectangle setCollisionRect();
    public Rectangle getCollisionRect();
    public Rectangle modifyCollisionRectSize(int width, int height);
    public Image getLastDirectionImg();
    public void setStandUpImg();
    public void setStandDownImg();
    public Image getStandLeftImg();
    public Image getStandRightImg();
    public void walkInDirectionCycle(String direction);
    public void walkUPImageCycle();
    public void walkDOWNImageCycle();
    public void walkRIGHTImageCycle();
    public void walkLEFTImageCycle();
    public Collection<Image> getMoveRIGHTImages();
    public Collection<Image> getMoveLEFTImages();
    public Collection<Image> getMoveDOWNImages();
    public Collection<Image> getMoveUPImages();
}
