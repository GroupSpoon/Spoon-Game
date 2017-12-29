package edu.swarthmore.cs.spoon.model.interfaces;

public interface ActionEndListener {
    /**
     * Listener that gets called when an action has been completed
     * @param actionId the id of the action that just finished
     */
    public void actionEnded(int actionId);
}
