package edu.swarthmore.cs.spoon.model.interfaces;

public interface LocAddedThingListener {


    /**
     * This method gets called when a Location adds a Thing to itself
     * @param locId the id of location that added the thing
     * @param thingId the id of the Thing
     * @param thing the Thing
     */
    public void thingAdded(int locId, int thingId, Thing thing);
}
