package edu.swarthmore.cs.spoon.model.implementations;

public class IDManager {
    private static int idCounter = 100;

    public static int assignId(){
        int toReturn = idCounter;
        idCounter += 1;
        return toReturn;
    }
}
//If we run into synchronization problems, consider changing the int to an Atomic Integer