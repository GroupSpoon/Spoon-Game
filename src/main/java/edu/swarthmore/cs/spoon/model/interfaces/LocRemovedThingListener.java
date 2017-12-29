package edu.swarthmore.cs.spoon.model.interfaces;

public interface LocRemovedThingListener {

    /**
     * This method gets called when a Location removes a Thing from itself
     * @param locId the id of location that removed the thing
     * @param thingId the id of the Thing
     * @param thing the Thing
     */
    public void thingRemoved(int locId, int thingId, Thing thing);
}
