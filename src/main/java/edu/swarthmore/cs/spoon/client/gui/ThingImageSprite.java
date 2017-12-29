package edu.swarthmore.cs.spoon.client.gui;

import edu.swarthmore.cs.spoon.client.gui.interfaces.GUIThingSprites;
import edu.swarthmore.cs.spoon.model.interfaces.Thing;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ThingImageSprite extends ImageView implements GUIThingSprites{
    private Shape spriteRep;
    private String urlPrefix = "/images/items/";
    private Thing actualThing;
    public ThingImageSprite(Thing thing) {
        Image img;
        actualThing = thing;

        //this.relocate(thing.getPosition().getULX(), thing.getPosition().getULY());
        try {
            System.out.println("Thing url: " + urlPrefix + thing.getName() + ".png");
            img = new Image(urlPrefix + thing.getName() + ".png");
        } catch(IllegalArgumentException e) {
            img = new Image("/images/blackgrid.jpeg");
        }
        this.setImage(img);
        this.setFitWidth(thing.getPosition().getWidth());
        this.setFitHeight(thing.getPosition().getHeight());



    }

    @Override
    public void renderTextures() {
        //Does not need to be used or do anything for these kinds of sprites.
    }
}
