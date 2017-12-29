package edu.swarthmore.cs.spoon.client.gui;

import edu.swarthmore.cs.spoon.client.gui.interfaces.GUIKeyInput;
import edu.swarthmore.cs.spoon.model.implementations.WorldImpl;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class BackendJVFXApp extends Application{
    private Stage window;
    private Map<Integer, PlayerCharacter> guiCharacters = new HashMap<>();
    private int clientID;
    private PlayerCharacter clientChar;
    private Pane rootPane;
    private BorderPane displayedLocationPane;
    private DialoguePane playerDialoguePane; //DialoguePane's have their position picked out for them.

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        rootPane = new BorderPane(); //Spans the entire window.
        clientID = 1; //THE CLIENT ID IS OFFICIALLY 1
        //displayedLocationPane = new BorderPane(); //A displayedLocationPane
        //displayedLocationPane.setStyle("-fx-background-color: pink");
        World guiWorld = new WorldImpl(); //This is the different world.
        GUIWorldManager worldManager = new GUIWorldManager(guiWorld);
        worldManager.makeCharactersInWorld(guiWorld);
        clientChar = guiWorld.getCharacter(clientID);
        LocationHome home = new LocationHome(clientChar); //Note: GUILocationPane

        //Creating characters
        for(PlayerCharacter each : guiWorld.getCharacters().values()) {
            guiCharacters.put(each.getId(), each); //Put the characters in a data structure so that they can be kept track of
            home.placePlayer(each); //home.placePlayer initially places a sprite on the board.
            each.addMovementListener(new MovementListener() {
                @Override
                public void thingMoved(int thingId, Position position) {
                    //System.out.println("Movemade");

                    Platform.runLater(() -> {
                        try {
                            home.relocatePlayer(guiCharacters.get(thingId));
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
                }
            });
        }



        System.out.println("Client Id: " + clientID);
        clientChar = guiCharacters.get(clientID);
        home.attachDialoguePane();
        //rootPane.setStyle("-fx-background-color: pink");
        //home.setStyle("-fx-background-color: pink");
        //home.setStyle("-fx-background-color: green");
        //displayedLocationPane.getChildren().addAll(home);
        rootPane.getChildren().addAll(home);
        Scene currScene = new Scene( rootPane, 1024, 1024 );//Passing the rootPane/group to currScene
        currScene.getStylesheets().add("/spoonstyle.css");
        stage.setScene(currScene); //Make currScene contents visible
        GUIMovementKeys keyInputObj = new GUIMovementKeys(currScene);
        keyInputObj.addMovementKeysToScene(currScene, clientChar); //Makes the current scene capable of accepting input and moving the client player
        keyInputObj.setClientSprite(home.getClientSprite());
        keyInputObj.setAllSprites(home.getAllPlayerSpritesInLocation());
        stage.setTitle("Spoon Game");
        stage.show();
//        System.out.println("rootPane width and height: " + rootPane.getWidth() + " " + rootPane.getHeight());
//        System.out.println("home width and height: " + home.getWidth() + " " + home.getHeight());
//        System.out.println("displayablelocation width and height: " + displayedLocationPane.getWidth() + " " + displayedLocationPane.getHeight());

        //LocationHome home = new LocationHome(guiCharacters.get(1));


        //#TODO: How does the backend know its client's player?
        new Timer().schedule(
                new TimerTask() {

                    @Override
                    public void run() {
                        guiWorld.timestep();
                    }
                }, 0, 5);


        //worldManager.backendTimeStep(guiWorld);

    }

    public int getClientID() {
        return clientID;
    }
}
