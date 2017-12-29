package edu.swarthmore.cs.spoon.model.interfaces;

public interface MovementListener {

    /**
     * This method gets called when a Thing's position is changed
     * @param thingId the id of this Thing
     */
    public void thingMoved(int thingId, Position position);

}
