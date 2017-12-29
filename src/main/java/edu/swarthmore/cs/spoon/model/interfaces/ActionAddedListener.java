package edu.swarthmore.cs.spoon.model.interfaces;

public interface ActionAddedListener {

    /**
     * gets called when a new action is added to a thing
     * @param actionId
     * @param action
     * @param thingId
     * @param thing
     */
    public void actionAdded(int actionId, Action action, int thingId, Thing thing);
}
