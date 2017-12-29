package edu.swarthmore.cs.spoon.model.interfaces;

public interface PcOpenedMapListener {
    /**
     * This gets called when a pc moves onto one of the entrance/exits of the location
     * @param pcId the id of the player character
     */
    public void pcOpenedMap(int pcId);
}
