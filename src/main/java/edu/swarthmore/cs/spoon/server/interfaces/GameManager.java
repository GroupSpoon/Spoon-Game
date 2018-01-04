package edu.swarthmore.cs.spoon.server.interfaces;

import edu.swarthmore.cs.spoon.model.interfaces.Thing;
import edu.swarthmore.cs.spoon.model.interfaces.World;

import java.io.IOException;

public interface GameManager {

    /**
     * saves the game state, called at the end of nighttime sequences
     */
    public void saveGame();

    /**
     * loads a previously saved gamefile
     */
    public void loadGame();

    /**
     * starts an instance of the game
     * @return the ip address of the server as a string usable by kryonet
     */
    public void startGame() throws IOException;

    public void playGame();

    public void stopGame();

    public World getWorld();

    public void startPlay();

    public void addTimeListener(TimeListener timeListener);

    public void removeTimeListener(TimeListener timeListener);

    public void addThingtoMap(Thing thing);

    void pause();

    void resume();

}
