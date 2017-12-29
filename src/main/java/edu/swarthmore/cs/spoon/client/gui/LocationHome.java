package edu.swarthmore.cs.spoon.client.gui;

import edu.swarthmore.cs.spoon.client.gui.interfaces.GUILocationPane;
import edu.swarthmore.cs.spoon.client.gui.interfaces.GUIPlayerSprites;
import edu.swarthmore.cs.spoon.client.gui.interfaces.GUIThingSprites;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.*; //Added this so that I can create a list field.

public class LocationHome extends Pane implements GUILocationPane  {
    Image homeBGimage;
    int lheight, lwidth;
    Location displayedLocation;
    List<PlayerCharacter> pcInLocation;
    Map<Integer, Node> nodeSpriteMap = new HashMap<>();
    Map<Integer, GUIPlayerSprites> playerSpritesMap = new HashMap<>();
    ArrayList<Color> pcColorList = new ArrayList<>();
    Map<Integer, Thing>thingMap;
    Map<Integer, GUIThingSprites> thingSpriteMap;
    PlayerCharacter clientChar;
    int clientID;
    Pane rootDisplayPane;
    DialoguePane playerDialoguePane;

    //View model object stores info about state the game.
    //Create view model at the start of app and share it with everyone
    //Listeners can check


    /**
     * In every constructor of a Location, the .setPrefSize() method needs to be called
     * to verify the Pane's location and height.
     * The method .generateIdentifiers() should also be run to generate unique identifiers for each
     * Player Sprite.
     */
    public LocationHome(PlayerCharacter player) {
        generateIdentifiers();
        this.displayedLocation = player.getLocation();
        clientID = player.getId();
        clientChar = player;
        this.setBackgroundTiles();
        //rootDisplayPane = rootLocPane; @Deprecated
        thingMap = new HashMap<>();
        thingSpriteMap = new HashMap<>();


        //RENDERING THINGS IN LOCATION
        for (Thing each : displayedLocation.getThings()) {
            //THING RENDERING
            System.out.println(each.getName());
            thingMap.put(each.getId(), each);

            if (each.getThingType() != ThingType.PC) {
                ThingImageSprite testThing = new ThingImageSprite(each);
                //Not a human Thing, probably an environmental object.
                each.addMovementListener(new MovementListener() {
                    @Override
                    public void thingMoved(int thingId, Position position) {
                        thingSpriteMap.get(thingId);
                    }
                });
                System.out.println("Thing height: " + each.getPosition().getHeight());
                System.out.println("Thing width: " + each.getPosition().getWidth());
                System.out.println("Thing name: " + each.getName());
                //Uncomment the line below to see a Rectangle placed where things belong
                //Rectangle testThing = new Rectangle(each.getPosition().getWidth(), each.getPosition().getHeight(), Color.PINK); //#TODO: how to render Things without breaking OCP?
                //ThingBaseSprite testThing = new ThingBaseSprite(each);

                //testThing.resize(each.getPosition().getWidth(), each.getPosition().getHeight());
                testThing.relocate(each.getPosition().getULX(), each.getPosition().getULY());
                testThing.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("Generating dialogue box");
                        Map<Integer, Action> actions = each.examine(player);
                        DialoguePane testPane = new DialoguePane(player, "Select an Action below");//#TODO: logic for getting the List<String> object.
                        for(Action each : actions.values()) {
                            //make a button out of each
                            testPane.addActionToPane(each);
                        }

                        //rootLocPane.getChildren().add(testPane);
                        LocationHome.this.getChildren().add(testPane);
                        //System.out.println("Bounds: " + testPane.getLayoutBounds());
                        //System.out.println("Root bounds: " + rootLocPane.getLayoutBounds());
                        //rootLocPane.setStyle("-fx-background-color: pink;"); //helpful debug statement to figure out what region rootLocPane has
                        //rootLocPane.getChildren().addAll(testPane);
                    }
                });
                this.getChildren().addAll(testThing);
            }
        }
    }

    public void attachDialoguePane() {
        playerDialoguePane = new DialoguePane(clientChar, "This is a home."); //Dialogue Pane at the bottom.
        this.getChildren().add(playerDialoguePane);
    }

    public LocationHome(Location location) {
        if (location == null) {
            System.out.println("Location is null");
            return;
        }
        this.lheight = location.getLocationHeight();
        this.lwidth = location.getLocationWidth();

        return;
    }

    @Override
    public void GUILocation() {

    }

    @Override
    public void setBackgroundTiles() {
        int accu = 0;
        for(int i = 0; i < 1024; i+= 16) {
            for(int j=0; j < 1024; j += 16) {
                Image tileBGImage = new Image("/images/woodtile.png");
                ImageView tileimgv = new ImageView();
                tileimgv.setImage(tileBGImage);
                tileimgv.toBack();
                tileimgv.setX(i);
                tileimgv.setY(j);
                this.getChildren().add(tileimgv);
            }
        }

    }

    public void placePlayer(Thing player) {
        //Create a PlayerSprite and position it as wanted
        System.out.println("Player name: " + player.getName());
        System.out.println("Player id: " + player.getId());
        //if (player.getId() == clientID) {
            System.out.println("client char detected.");
            DemoChar1 demochar = new DemoChar1(clientChar, clientID, "/images/demochar" + player.getId());
            //MAKE DISPLAYABLE BORDER
            //demochar.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            nodeSpriteMap.put(player.getId(), demochar);
            playerSpritesMap.put(player.getId(), demochar);
            demochar.relocate(player.getPosition().getULX(), player.getPosition().getULY());
            this.getChildren().add(demochar);
            return;
       /* }
        PlayerSprite playerSprite = new PlayerSprite((PlayerCharacter) player, player.getId(), pcColorList.get(player.getId()));
        nodeSpriteMap.put(player.getId(), playerSprite); //Store each Player in a readily accessible hashmap to the pane
        playerSprite.relocate(player.getPosition().getUL().getX(), player.getPosition().getUL().getY());
        this.getChildren().add(playerSprite);*/
    }



    @Override
    public void relocateThing(Thing player) {
        System.out.println(nodeSpriteMap.get(player.getId()));
        this.nodeSpriteMap.get(player.getId()).relocate(player.getPosition().getUL().getX(), player.getPosition().getUL().getY());
        System.out.println("In Relocate Player. Position: " + player.getPosition().getUL().getX() + player.getPosition().getUL().getY());
    }

    public void relocatePlayer(PlayerCharacter player) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(player.getId() == clientID) {
            System.out.println("Movement state: " + player.getMovState().toString());
            //Method method = GUIPlayerSprites.class.getMethod("walk" + player.getMovState().toString() + "ImageCycle");
            //method.invoke(this.playerSpritesMap.get(player.getId()));
            this.nodeSpriteMap.get(player.getId()).relocate(player.getPosition().getULX(), player.getPosition().getULY());
        }
    }

    public void stopPlayerInDirection(PlayerCharacter player) {
        //this.playerSpritesMap.get(player.getId()).se
    }

    @Override
    public void placeThing(Thing thing) {
        //#TODO: it's problematic that this method is called PlaceThing when we really want it to refer to non-player Things.
        //

    }

    public GUIPlayerSprites getClientSprite() {
        return this.playerSpritesMap.get(clientID);
    }

    public Map<Integer, GUIPlayerSprites> getAllPlayerSpritesInLocation() {
        return this.playerSpritesMap;
    }

    public void generateIdentifiers() {
        //A method that is responsible for generating a mechanism for identifying different players
        //#TODO: Make this method able to accomodate 8 unique identifiers per player
        //#TODO: this is bad design principle, I probably could find an alterative to creating a list of color enums.
        pcColorList.add(Color.RED);
        pcColorList.add(Color.BLUE);
        pcColorList.add(Color.DARKCYAN);
        pcColorList.add(Color.GREEN);
        pcColorList.add(Color.PINK);
        pcColorList.add(Color.LIGHTBLUE);
        pcColorList.add(Color.ORANGE);
        pcColorList.add(Color.BROWN);
        pcColorList.add(Color.BURLYWOOD);
    }

}
