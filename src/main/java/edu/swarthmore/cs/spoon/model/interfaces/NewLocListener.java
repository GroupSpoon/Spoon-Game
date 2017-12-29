package edu.swarthmore.cs.spoon.model.interfaces;

public interface NewLocListener {

    /**
     * This method gets called when the World creates a new Location
     * @param locId the id of the Location that was just created
     */
    public void newLocCreated(int locId);
}
