package edu.swarthmore.cs.spoon.model.interfaces;

import java.util.List;

/**
 * Handles the logic of the maze minigame: gennerates the maze, and keeps track of items found.
 */
public interface MazeGame {
    /**
     * uses players spoon and helpfulness stats to claculate the time alloted for the maze
     * @param players a list of all player character objects
     * @return the time limit to be used
     */
    public int calculateTimeLimit(List<PlayerCharacter> players);

    /**
     * starts a timer using the time limit calculated by calculateTimeLimit
     */
    public void startTimer();

    /**
     * create the maze location containing a number of items to find equal to twice the number of players
     */
    public void generateMaze();

    /**
     * called every time a player finds an item, adds it to items found cost and renoves it from the maze
     * @param position the position of the item found
     */
    public void foundItem(Position position);

}
