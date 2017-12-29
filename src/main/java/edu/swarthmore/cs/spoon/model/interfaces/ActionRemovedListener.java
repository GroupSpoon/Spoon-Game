package edu.swarthmore.cs.spoon.model.interfaces;

public interface ActionRemovedListener {
    /**
     * gets called when an action is removed from a thing
     * @param actionId
     * @param action
     * @param thingId
     * @param thing
     */
    public void actionRemoved(int actionId, Action action, int thingId, Thing thing);
}
