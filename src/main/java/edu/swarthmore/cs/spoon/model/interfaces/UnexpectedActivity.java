package edu.swarthmore.cs.spoon.model.interfaces;

import javafx.util.Pair;

/**
 * An event that might occur when the player tries to perform an Action that costs spoons.
 * UnexpectedActivities might end up costing the player more spoons, giving the player back some spoons,
 * taking an item away from the player, giving the player an item, changing the player's money, or nothing at all.
 */
public interface UnexpectedActivity {
    /**
     * Setter for activity name
     * @param name - String to set the activity name to
     */
    public void setActivityName(String name);


    /**
     * Getter for activity name
     * @return a string that is the name for this activity
     */
    public String getActivityName();

    /**
     * Setter for spoon cost. Spoon cost could be negative for a normal cost, 0 for no spoon cost, or positive for a spoon gain
     * @param cost an integer to set the spoon cost to
     */
    public void setSpoonCost(int cost);

    /**
     * Getter for spoon cost
     * @return an int that is the spoon cost for this activity
     */
    public int getSpoonCost();

    /**
     * Setter for money cost. Money cost could be negative for a normal cost, 0 for no money cost, or positive for a money gain
     * @param moneyCost an integer to set the money cost to
     */
    public void setMoneyCost(int moneyCost);

    /**
     * Getter for money cost
     * @return an int that is the money cost of this activity
     */
    public int getMoneyCost();

    /**
     * Setter for item cost. An activity cannot cost multiple types of item. Cost could be negative for a loss of some amount of the specified item,
     * 0 for no cost, or positive for items gained.
     * @param item the item to take away (or give to the player if the quantity is positive, or neither if the quantity is 0)
     * @param itemCost an integer to set the item cost to.
     */
    public void setItemCost(Item item, int itemCost);

    /**
     * Getter for item cost
     * @return a pair of type Item (for the item gained or lost) and Integer (for the amount gained or lost)
     */
    public Pair<Item, Integer> getItemCost();


    /**
     * Setter for the text displayed when the activity occurs
     * @param activityText a String to display when the activity occurs
     */
    public void setActivityText(String activityText);


    /**
     * Getter for the text displayed when the activity occurs
     * @return a String that is displayed when the activity occurs
     */
    public String getActivityText();
    
}
