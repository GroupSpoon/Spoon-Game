package edu.swarthmore.cs.spoon.model.interfaces;

public interface NewPCListener {
    /**
     * This method gets called when the World creates a new PlayerCharacter
     * @param pcId the id of the PlayerCharacter that was just created
     */
    public void newPCCreated(int pcId);
}
