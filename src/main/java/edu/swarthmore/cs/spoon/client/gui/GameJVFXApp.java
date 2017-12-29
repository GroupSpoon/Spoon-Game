//package edu.swarthmore.cs.spoon.client.gui;
//
////import edu.swarthmore.cs.spoon.client.WorldClient;
////import edu.swarthmore.cs.spoon.client.bbiney1.WorldClientImpl;
//import edu.swarthmore.cs.spoon.model.implementations.WorldImpl;
//import edu.swarthmore.cs.spoon.model.interfaces.*;
//import edu.swarthmore.cs.spoon.client.network.GameClientImpl;
//import edu.swarthmore.cs.spoon.client.network.interfaces.GameClient;
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.event.EventHandler;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
//import javafx.stage.Stage;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
////Call PlayerCharacter.setMovState(Direction dir) on each input.
////Have listener on PlayerCharacter (it'ss called MovementListener) on PC's
////Every timestep the player moves, my listeners will be notified.
//
////#TODO: ADD LISTENER TO WORLD FOR CREATION OF LOCATIONS
//public class GameJVFXApp extends Application {
//    Scene currScene;
//    Stage window;
//    HBox buttonBox;
//    private int clientID;
//    PlayerCharacter clientChar;
//    private Location clientCurrLocation;
//    private Map<Integer, PlayerCharacter> guiCharacters = new HashMap<>(); //Key: ID (type: integer). Value: PlayerCharacter object
//    ArrayList<Paint> pcColorList; //A list of 4 colors for 8 players. In order to use a color identifier in
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        window = stage;
//        pcColorList = new ArrayList<>();
//        generateIdentifiers();
//        Group rootPane = new Group(); //This is the root pane
//
//        // USE NETWORK IMPLEMENTATION
//        GameClient guiClient;
//        /* TODO: PUT THIS BACK
//        try {
//            guiClient = new GameClientImpl("127.0.0.1"); //#TODO: make GameClientImpl
//        } catch(Exception e) {
//            System.out.println("Failure to create GameClientImpl");
//            return;
//        }*/
//        clientID = 1; //TODO: guiClient.getOwnId();
//        World guiWorld = new WorldImpl();//TODO: guiClient.getWorld();
//        /*System.out.println("Client ID: " + clientID);
//        guiWorld.createCharacter("Bob", 1);
//        guiWorld.createCharacter("Spork", 2);*/
//
//
//
//
//
//        /* My pseudocode for the StartGameListener.
//        guiClient.addListener(new StartGameListener() {
//            @Override
//            public void startGame(World world) {
//                List<PlayerCharacter> characterList = world.getCharacters();
//                System.out.println("StartGameListener handler");
//                System.out.println("size of world.getCharacters: " + characterList.size());
//                for (Playercharacter each : characterList) {
//                    //...
//                }
//            }*/
//
//       // World guiWorld = serverWorld.getWorld();
//
//        //USE BACKEND IMPLEMENTATION
//        //World guiWorld = new WorldImpl();
//
//        BorderPane locationPane = new BorderPane(); //locationPane designated for rendering a Location
//        int id=1;
//        //Making location. In this case, we only have one Location: LocationHome
//        LocationHome home = new LocationHome(guiWorld); //Currently a location is represented by a pane.
//        List<PlayerCharacter> characters = guiWorld.getCharacters(); //Getting characters from the guiworld,
//        System.out.println("Character count: " + characters.size());
//
//        if(characters.size() == 0) {
//            //This means that guiWorld.getCharacters() did not return anything
//            System.out.println("No characters retrieved from guiWorld.getCharacters(). This program may crash as a result of trying to perform perations on an empty list.");
//        }
//
//
//        for(PlayerCharacter each : characters) { //This is a for-loop that will "process" each character that was returned by guiWorld.getCharacters()
//            System.out.println("Analyzing PlayerChar with ID: " + each.getId());
//            guiCharacters.put(each.getId(), each);
//            if(each.getId() == clientID) {
//                System.out.println("In getCharacters() for loop -  Main client of this world.");
//                clientChar = each;
//                //Render the location of the player and all people within it
//                clientCurrLocation = clientChar.getLocation();
//                //System.out.println(clientCurrLocation);
//            }
//            //guiCharacters.put(id, each);
//            home.placePlayer(each);
//            //Add movement listeners to characters
//            each.addMovementListener(new MovementListener() {
//                @Override
//                public void thingMoved(int pcid, Position position) {
//                     Platform.runLater(()->home.relocateThing(guiCharacters.get(pcid))); //This line calls the method in Locationhome.java
//                    //in order to move the playersprite. If something is wrong, it's because of this code block.
//                }
//            });
//            //id++;
//        }
//        locationPane.getChildren().addAll(home);
//        rootPane.getChildren().add(locationPane);
//        rootPane.setStyle("-fx-background-image: url(" + "'images/blackgrid.jpeg'" + ");" + "-fx-background-size: cover;");
//
//        currScene = new Scene( rootPane, 1024, 1024 ); //Passing the rootPane/group to currScene
//        stage.setScene(currScene); //Make currScene contents visible
//        stage.setTitle("Spoon Game");
//        currScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                switch(keyEvent.getCode()) {
//                    //#TODO: figure out another mechanism for getting the player
//                    case UP: guiCharacters.get(clientID).setMovState(Direction.UP); System.out.println("movState set to UP"); break; //setMovState(Direction.UP); break;
//                    case DOWN: guiCharacters.get(clientID).setMovState(Direction.DOWN); System.out.println("movState set to DOWN"); break;
//                    case RIGHT:guiCharacters.get(clientID).setMovState(Direction.RIGHT); System.out.println("movState set to RIGHT"); break;
//                    case LEFT:guiCharacters.get(clientID).setMovState(Direction.LEFT); System.out.println("movState set to LEFT"); break;
//                }
//            }
//        });
//
//        currScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                guiCharacters.get(clientID).setMovState(Direction.NONE); System.out.println("movState set to NONE");
//            }
//        });
//        stage.show();
//
//
//    }
//
//    public Map<Integer, PlayerCharacter> getGuiCharacters() {
//        return guiCharacters;
//    }
//
//    public void renderLocation(PlayerCharacter player) {
//        Location playerLocation = player.getLocation();
//    }
//
//    public void renderLocation(Location loc) {
//        //
//    }
//
//    public void generateIdentifiers() {
//        //A method that is responsible for generating a mechanism for identifying different players
//        //#TODO: Make this method able to accomodate 8 unique identifiers per player
//        //#TODO: this is bad design principle, I probably could find an alterative to creating a list of color enums.
//        pcColorList.add(Color.RED);
//        pcColorList.add(Color.BLUE);
//        pcColorList.add(Color.YELLOW);
//        pcColorList.add(Color.GREEN);
//        pcColorList.add(Color.PINK);
//        pcColorList.add(Color.LIGHTBLUE);
//        pcColorList.add(Color.ORANGE);
//        pcColorList.add(Color.BROWN);
//        pcColorList.add(Color.BURLYWOOD);
//    }
//    public void playGame(World guiWorld) {
//        while (true) {
//            try {
//                Thread.sleep(30);
//            } catch (InterruptedException e) {
//
//            }
//
//            guiWorld.timestep();
//
//        }
//    }
//
//
//}
//
