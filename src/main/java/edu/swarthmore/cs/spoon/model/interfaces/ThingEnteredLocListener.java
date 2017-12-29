package edu.swarthmore.cs.spoon.model.interfaces;

public interface ThingEnteredLocListener {

    /**
     * This method gets called when a thing enters a new Location
     * @param locId the id of the location that was entered
     * @param  thingId id of the thing
     * @param thing the thing
     */
    public void ThingEntered(int locId, int thingId, Thing thing);
}
