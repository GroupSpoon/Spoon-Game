package edu.swarthmore.cs.spoon.client.gui;

import edu.swarthmore.cs.spoon.client.gui.interfaces.GUIKeyInput;
import edu.swarthmore.cs.spoon.client.gui.interfaces.GUIPlayerSprites;
import edu.swarthmore.cs.spoon.client.network.GameClientImpl;
import edu.swarthmore.cs.spoon.model.interfaces.Direction;
import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class GUIMovementKeys implements GUIKeyInput{
    private Scene currScene;
    private PlayerCharacter clientChar;
    private GUIPlayerSprites clientSprite;
    private String lastKeyPressed;
    private Map<Integer, GUIPlayerSprites> mapOfSprites;
    private final Logger logger = LoggerFactory.getLogger(GUIMovementKeys.class);

    public GUIMovementKeys(Scene scene) {
        /**
         * A constructor that needs a scene object so that it knows what to attach its movement keys to.
         */
        this.currScene = scene;
        mapOfSprites = new HashMap<>();
    }

    public void setClientSprite(GUIPlayerSprites cSprite) {
        clientSprite = cSprite;
    }

    public void setAllSprites(Map<Integer, GUIPlayerSprites> pcMap) {
        mapOfSprites = pcMap;
    }

    @Override
    public void addMovementKeysToScene(Scene scene, PlayerCharacter player) {
        clientChar = player;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                lastKeyPressed = keyEvent.getCode().toString();
               // System.out.println("LKEY: " + lastKeyPressed);
                System.out.println("Key code: " + keyEvent.getCode());

                switch(keyEvent.getCode()) {

                    //#TODO: figure out another mechanism for getting the player
                    case UP: clientChar.setMovState(Direction.UP); clientSprite.walkUPImageCycle(); break;//System.out.println("problematic player id: " + player.getId()); System.out.println("What happens: " + mapOfSprites); System.out.println("size of map: " + mapOfSprites.size()); clientSprite.walkUPImageCycle(); break;//mapOfSprites.get(player.getId()).walkUPImageCycle(); break; //System.out.println("UP position player:" + guiCharacters.get(clientID).getPosition().getUL().getX() + guiCharacters.get(clientID).getPosition().getUL().getY()); break;
                    case DOWN: clientChar.setMovState(Direction.DOWN); clientSprite.walkDOWNImageCycle(); System.out.println("movState set to DOWN"); break;
                    case RIGHT:clientChar.setMovState(Direction.RIGHT); clientSprite.walkRIGHTImageCycle(); System.out.println("movState set to RIGHT"); break;
                    case LEFT:clientChar.setMovState(Direction.LEFT); clientSprite.walkLEFTImageCycle(); System.out.println("movState set to LEFT"); break;
                }



            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                Direction movstate = clientChar.getMovState();
                if(movstate == null) {
                    logger.info("movstate is null for some reason?!?!");
                }
                clientChar.setMovState(Direction.NONE);
                logger.info("clientChar ID at the suspicious line of code: " + clientChar.getId());
                //logger.info("setting still image: " + clientChar.getMovState().toString());
                clientSprite.setStillImg(movstate.toString());//lastKeyPressed);
                 //mapOfSprites.get(player.getId()).setStillImg(lastKeyPressed);
                System.out.println("movState set to NONE");

            }
        });
    }

    @Override
    public void removeMovementKeysFromScene(Scene scene) {

    }
}
