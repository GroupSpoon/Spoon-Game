package edu.swarthmore.cs.spoon.model.interfaces;

public interface NewThingListener {
    /**
     * This method gets called when the World creates a new Thing
     * @param thingId the id of the Thing that was just created
     *
     */
    public void newThingCreated(int thingId, Thing thing);
}
