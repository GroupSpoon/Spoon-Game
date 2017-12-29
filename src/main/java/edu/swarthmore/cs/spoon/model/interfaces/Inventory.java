package edu.swarthmore.cs.spoon.model.interfaces;

import javafx.util.Pair;

import java.util.List;

/**
 * Keeps track of all the items that a player is currently carrying
 */
public interface Inventory {
    /**
     * Returns a list of all the items in the inventory
     * @return a list of items that is the contents of the inventory
     */
    public List<Item> getItems();

    /**
     * Returns a list of pairs of the items in the inventory and the quantities of each item
     * @return a list of pairs of items and their quantities
     */
    public List<Pair<Item,Integer>> getItemsWithQuantities();

    /**
     * Adds the specified item to the inventory
     * @param item the Item to be added
     * @param quantity the amount of that Item to be added
     */
    public void addItem(Item item, int quantity);

    /**
     * Removes the specified quantity of the specified item from the inventory, and returns it (or null if that item was not in the inventory)
     * @param item the item to be removed
     * @param quantity the amount of that item to be removed
     * @return item, the item that was removed, or null if item was not in the inventory
     */
    public Item removeItem(Item item, int quantity);

    /**
     * Removes all of the specified item from the inventory, and returns it (or null if that item was not in the inventory)
     * @param item the item to be removed
     * @return item, the item that was removed, or null if item was not in the inventory
     */
    public Item removeAllOfItem(Item item);

    /**
     * Checks whether the inventory contains a particular item
     * @param item the item to check
     * @return true if the inventory has that item, false if it does not
     */
    public Boolean hasItem(Item item);

    /**
     * getter for the quantity of a particular item that the inventory contains
     * @param item -the item to check the quantity of
     * @return an int that is the amount of that item that the inventory has. If it doesn't have any, return 0
     */
    public int getQuantityOfItem(Item item);

}
