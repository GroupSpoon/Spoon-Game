package edu.swarthmore.cs.spoon.model.interfaces;

import edu.swarthmore.cs.spoon.common.messages.Requests;

public interface MovStateChangeListener {
    /**
     * Returns the player's new movement state.
     * @param
     */
    public void thingStateChanged(int id, Direction direction);
}
