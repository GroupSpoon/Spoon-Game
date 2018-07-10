package edu.swarthmore.cs.spoon.client.gui;

import edu.swarthmore.cs.spoon.client.gui.interfaces.GUIDialoguePane;
import edu.swarthmore.cs.spoon.model.interfaces.Action;
import edu.swarthmore.cs.spoon.model.interfaces.ActionEndListener;
import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;
import edu.swarthmore.cs.spoon.model.interfaces.Thing;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

public class DialoguePane extends Pane implements GUIDialoguePane{

    private Collection<Action> actionsForDialogue;
    private Queue<String> stringQueue;
    private String visibleString;
    private Text visibleText;
    private Rectangle invisTextBox;
    private TextArea visibleTextArea;
    private final int CHAR_MAX = 92;
    private Pane testPane;
    private Pane actionButtonPane;
    private HBox actionButtonHbox;
    private PlayerCharacter clientPlayer;
    private int dpaneX, dpaneY; //These integers should be the position of the pane relative to the entire window
    private EventHandler<MouseEvent> clickableListener;

    //IMPORTANT: Maximum of 91-93 characters for one display box.

    public DialoguePane(PlayerCharacter player, String initialCaption) {
        super();
        this.clientPlayer = player;
        dpaneX = 700; //Center
        dpaneY = 100; //(32 * 8) - 64 == 896 - 64
        this.resizeRelocate(287, 100, 450, 140);
        System.out.println("DialoguePane Constructor");
        this.setStyle("-fx-background-color: rgba(0, 0, 0, .95)");
        //this.setArcHeight(20);
        //this.setArcWidth(20);
        this.setDisplayableText(initialCaption);
        this.makeActionButtonHBox(); //Makes an ActionButtonPane
        clickableListener =  new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DialoguePane.this.setVisible(false);
            }
        };
        makeClickable();
        //setDisplayableText("Welcome to spoon game");
    }

    public void makeThingTextVisible(Thing thing) {
        //
    }


    public void makeActionButtonHBox() {
        actionButtonHbox = new HBox();
        actionButtonHbox.setMinSize(450, 40);
        System.out.println(actionButtonHbox.backgroundProperty());
        actionButtonHbox.resizeRelocate(0, 100, 400, 40);
        actionButtonHbox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        //actionButtonHbox.setStyle("-fx-background-color: blue"); //#TODO: How do I get this background to show up above the other
        actionButtonHbox.toFront();
        System.out.println("After " + actionButtonHbox.backgroundProperty());
        this.getChildren().add(actionButtonHbox);
    }
    public void makeClickable() {
        visibleTextArea.setOnMouseClicked(clickableListener);
    }

    public void makeUnClickable() {
        visibleTextArea.setOnMouseClicked(null);
    }

    public void setDisplayableText(String string) {
        //Function for setting visible text on the dialogue box
        if(this.validateString(string) == false) {
            //Chop up the string into 92 character strings

        }
        visibleTextArea = new TextArea(string);
        /*ScrollBar scrollBarv = (ScrollBar)visibleTextArea.lookup(".scroll-bar:vertical");
        scrollBarv.setDisable(true);*/ //#TODO: This originally worked and now it doesn't. It gives a null pointer exception. Come back to it.
        visibleTextArea.setFont(Font.font("Verdana", 20));
        visibleTextArea.setEditable(false);
        //visibleTextArea.setFocusTraversable(false);
        /*visibleTextArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("clicked text area");
                DialoguePane.this.setVisible(false);

            }
        });*/
        //this.setVisible(false);
        visibleTextArea.setWrapText(true);
        visibleTextArea.setPrefRowCount(4);
        visibleTextArea.setPrefWidth(380);
        visibleTextArea.setPrefHeight(100);
        visibleTextArea.setStyle("-fx-background-color: rgba(0, 0, 0, .95)");
        this.layoutInArea(visibleTextArea, 10, 0, 300, 300, 5, Insets.EMPTY, HPos.LEFT, VPos.BASELINE);
        this.getChildren().add(visibleTextArea);
    }

    public void changeDisplayableText(String string) {
        //#TODO: Implement a way to change the text of the textarea object in DialoguePanes.

    }

    public void addActionToPane(Action action) {
        System.out.println("Adding Action: " + action.getActionName());
        Button actButton = new Button(action.getActionName());
        actButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                visibleTextArea.clear();
                System.out.println("performing Action: " + action.getActionName());
                /*for(String each : action.startActionMessage()) {
                    visibleTextArea.appendText(each);
                }
                for (String each : action.endActionMessage()) {
                    visibleTextArea.appendText(each);
                }*/
                visibleTextArea.appendText(action.getActionDescription());
                ActionEndListener listener = new ActionEndListener() {
                    @Override
                    public void actionEnded(int actionId, boolean success) {
                        Platform.runLater(() ->

                                displayEndMessage(action, success)
                        );
                    }
                };
                makeUnClickable();
                clientPlayer.initiateAction(action, listener);
            }
        });
        this.actionButtonHbox.getChildren().add(actButton);
    }

    public boolean validateString(String string) {
        if(string.length() < CHAR_MAX) {
            return true;
        } else {
            return false;
        }
    }
    public Collection<String> chopUpLargeString(String string) {
        Collection<String> choppedUpCollection = new ArrayList<>();
        return null;
    }

    public void displayEndMessage(Action action, boolean success) {
        //IMPORTANT
        //A separate function that was created due to difficulties with javafx threads potentially interacting with kryonet. This function
        //MUST remain as its own method or must be wrapped with a Platform RunLater.

        List<String> message;
        if (success) {
            message = action.endActionMessage();
        }
        else {
            message = new ArrayList<>();
            message.add("You don't have the spoons for this");
        }
        visibleTextArea.clear();
        for (String string : message) {
            visibleTextArea.appendText(string);
        }
        this.makeClickable();
        System.out.println("An Action has not only commenced but has also, in truth, ended. w0wzers.");

    }

    public TextArea getVisibleTextArea() {
        return visibleTextArea;
    }

    public void addToTextArea(String string) {
        visibleTextArea.appendText(string);
    }
}
