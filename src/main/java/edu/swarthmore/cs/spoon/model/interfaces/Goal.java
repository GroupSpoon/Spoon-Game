package edu.swarthmore.cs.spoon.model.interfaces;

import java.util.List;

/**
 * A task that needs to be completed by a specified deadline
 */
public interface Goal {

    /**
     * Each Goal object has a short description, a String that takes a brief sentence to describe what the goal is.
     * @param text: A string that should comprise the short description of a Goal
     */
    public void setShortDescription(String text);

    /**
     * A getter function for the short description of a Goal
     * @return a string indicating the short description of the goal
     */
    public String getShortDescription();

    /**
     * A goal may have a longer description that details the Goal. This method takes in a List of sentences and sets the long description for the goal.
     * @param text: A List of Strings, each String being about a sentence long that typically separates dialogue boxes that appear on screen.
     */
    public void setLongDescription(List<String> text);

    /**
     * A getter function for the long description of a Goal, concatenates all of the Strings within the List of Strings for the long description.
     * @return
     */
    public String getLongDescription();

    /**
     * A Goal always has a deadline. Deadline's by default are set for the end of the game campaign, otherwise calling this function will set the deadline for a particular in-game day
     * @param days: an integer representing the day of the deadline.
     */
    public void setDeadLine(int days);

    /**
     * A getter function for the deadline
     * @return an integer indicating the date of the deadline for this goal.
     */
    public int getDeadline();

    /**
     * A Goal is either complete or incomplete.
     * @return true, if the goal is complete, OR false if the goal is not complete.
     */
    public Boolean isComplete();


}
