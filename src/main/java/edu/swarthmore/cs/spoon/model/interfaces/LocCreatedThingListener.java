package edu.swarthmore.cs.spoon.model.interfaces;

public interface LocCreatedThingListener {

    /**
     * This method gets called when a Location creates a new Thing
     * @param locId the id of location that created the thing
     * @param thingId the id of the new Thing
     * @param thing the new Thing
     */
    public void thingCreated(int locId, int thingId, Thing thing);

}
