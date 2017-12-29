package edu.swarthmore.cs.spoon.client.gui;

import edu.swarthmore.cs.spoon.client.gui.interfaces.GUIWorldOrganizer;
import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;
import edu.swarthmore.cs.spoon.model.interfaces.World;
import edu.swarthmore.cs.spoon.server.interfaces.GameState;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GUIWorldManager implements GUIWorldOrganizer{
    World managerWorld;
    int time = 0;
    Collection<PlayerCharacter> pcList = new ArrayList<>();
    GameState state = GameState.WAITING;

    public GUIWorldManager(World world) {
        this.managerWorld = world;
        pcList = world.getCharacters().values();
    }

    public void makeCharactersInWorld(World world) {
        world.createCharacter("Back1", 1);
        world.createCharacter("Back2", 2);
        state = GameState.PLAYING;
    }


    @Override
    public Scene createLocationScene(World world, Pane rootPane) {
        Scene primaryScene;
        //First,
        return null;
    }

    @Override
    public List<PlayerCharacter> retrieveCharacters() {
        return null;
    }
}
