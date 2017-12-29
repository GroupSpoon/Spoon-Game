package edu.swarthmore.cs.spoon.model.interfaces;

public interface ThingFactory {


    /**
     * Creates a thing with the specified parameters
     * @param name What is its name
     * @param loc where does it live
     * @param pos where is it in that location?
     * @param holder holds the listeners for a new thing getting created
     * @param type what type of thing is it
     * @param lookAtText what should it say when you try to look at it
     * @param solid should it be solid?
     * @return the Thing you have created
     */
    public Thing createThing(String name, Location loc, Position pos, ThingListenersHolder holder, ThingType type, String lookAtText, boolean solid);

}
