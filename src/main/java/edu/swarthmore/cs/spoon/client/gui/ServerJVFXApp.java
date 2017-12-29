package edu.swarthmore.cs.spoon.client.gui;

import edu.swarthmore.cs.spoon.client.network.GameClientImpl;
import edu.swarthmore.cs.spoon.client.network.interfaces.GameClient;
import edu.swarthmore.cs.spoon.client.network.interfaces.StartGameListener;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServerJVFXApp extends Application {

    private Stage window;
    private GameClient guiClient;
    private int clientID;
    private Scene currScene;
    private PlayerCharacter clientChar;
    private Location clientCurrLocation;
    private Map<Integer, PlayerCharacter> guiCharacters = new HashMap<>(); //Key: ID (type: integer). Value: PlayerCharacter object
    ArrayList<Paint> pcColorList; //A list of 4 colors for 8 players. In order to use a color identifier in
    private Pane rootPane;
    private BorderPane displayedLocationPane;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        rootPane = new BorderPane();
        displayedLocationPane = new BorderPane();

        try {
            guiClient = new GameClientImpl("127.0.0.1"); //#TODO: make GameClientImpl
        } catch (Exception e) {
            System.out.println("Failure to create GameClientImpl");
            return;
        }

        World guiWorld = guiClient.getWorld(); //Gets the world.
        currScene = new Scene(rootPane, 1024, 1024);
        GUIMovementKeys keyInputObj = new GUIMovementKeys(currScene); //This initializes the KeyInputObj, however the KeyInputObj can't be attached until the render world/start world function.


        guiClient.addStartGameListener(new StartGameListener() {
            @Override
            public void startGame(World world) {
                System.out.println("The GUI knows we've started");
                //StartGameListener has one method, startGame. This can take in a World object and render the location of the first player completely.
                //#TODO: startGame should only render the world and location relative to its client.
                Platform.runLater(()-> {
                    guiCharacters = guiWorld.getCharacters();
                    clientID = guiClient.getOwnId();
                    clientChar = guiCharacters.get(clientID);
                    clientCurrLocation = clientChar.getLocation();
                    LocationHome home = new LocationHome(clientChar); //#TODO: Consider creating an object that encapsulates important information (such as the client
                    displayedLocationPane.getChildren().addAll(home);

                    for (PlayerCharacter each : guiWorld.getCharacters().values()) {
                        home.placePlayer(each); //Each time this method is called, a player is placed
                        each.addMovementListener(new MovementListener() {
                            @Override
                            public void thingMoved(int thingId, Position position) {
                                System.out.println("Movemade");
                                Platform.runLater(() -> home.relocateThing(guiCharacters.get(thingId)));
                            }
                        });


                    } //end of for loop
//                    rootPane.getChildren().addAll(locationPane);
//                    GUIMovementKeys keyInputObj = new GUIMovementKeys(currScene);
//                    keyInputObj.addMovementKeysToScene(currScene, clientChar);
                    keyInputObj.setClientSprite(home.getClientSprite());
                    keyInputObj.setAllSprites(home.getAllPlayerSpritesInLocation());
                    keyInputObj.addMovementKeysToScene(currScene, clientChar);
                    rootPane.getChildren().add(displayedLocationPane);


                });

            }
        });



        stage.setScene(currScene); //Make currScene contents visible
        stage.setTitle("Spoon Game");
        stage.show();
    }

    public int getClientID() {
        return clientID;
    }

}
