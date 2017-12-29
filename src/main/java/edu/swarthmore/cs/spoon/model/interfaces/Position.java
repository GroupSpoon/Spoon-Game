package edu.swarthmore.cs.spoon.model.interfaces;

import java.util.List;
import java.util.Optional;

/**contains the four corner points of a Thing**/
public interface Position {

    /**
     * getter for the four corner Points of this position
     * @return the four corner Points of this position, in this order: Upper left, upper right, lower left, lower right
     */
    public List<Point> getPoints();

    /**
     * Getter for the x and y vals of the four corner Points as ints
     * To make writing tests less annoying
     * @return Upper left X, upper left Y, Upper right X, upper Right Y, Lower left X, lower left y, lower right x, lower right y
     */
    public List<Integer> getPointsAsInts();


    /**
     * getter for upper left point
     * @return upper left point
     */
    public Point getUL();

//    /**
//     * setter for upper left point
//     * @param newUL the point to make the new upper left be
//     * @return boolean true if a valid point was entered for this corner (essentially, is the Thing still rectangular with this change having been made?)
//     */
//    public Boolean setUL(Point newUL);

    /**
     * getter for upper right point
     * @return upper right point
     */
    public Point getUR();

//    /**
//     * setter for upper right point
//     * @param newUR the point to make the new upper right be
//     * @return boolean true if a valid point was entered for this corner (essentially, is the Thing still rectangular with this change having been made?)
//     */
//    public Boolean setUR(Point newUR);

    /**
     * getter for lower left point
     * @return lower left point
     */
    public Point getLL();

//    /**
//     * setter for lower left point
//     * @param newLL the point to make the new lower left be
//     * @return boolean true if a valid point was entered for this corner (essentially, is the Thing still rectangular with this change having been made?)
//     */
//    public Boolean setLL(Point newLL);

    /**
     * getter for lower right point
     * @return lower right point
     */
    public Point getLR();

//    /**
//     * setter for lower right point
//     * @param newLR the point to make the new lower right be
//     * @return boolean true if a valid point was entered for this corner (essentially, is the Thing still rectangular with this change having been made?)
//     */
//    public Boolean setLR(Point newLR);

    /**
     * Getter for the upper left corner point's x value
     * @return
     */
    public int getULX();

    /**
     * Getter for the upper left corner point's y value
     * @return
     */
    public int getULY();

    /**
     * Getter for the height of the Position
     * @return
     */
    public int getHeight();

    /**
     * Getter for the width of the Position
     * @return
     */
    public int getWidth();

    /**
     * moves the position to the left by the specified amount
     * @param amount the amount to move
     * @return the new position
     */
    public Optional<Position> moveLeft(int amount);

    /**
     * moves the position to the right by the specified amount
     * @param amount the amount to move
     * @return the new position
     */
    public Optional<Position> moveRight(int amount);

    /**
     * moves the position up by the specified amount
     * @param amount the amount to move
     * @return the new position
     */
    public Optional<Position> moveUp(int amount);

    /**
     * moves the position down by the specified amount
     * @param amount the amount to move
     * @return the new position
     */
    public Optional<Position> moveDown(int amount);

    /**
     * Moves the position in the specified direction by the specified amount
     * @param amount the amount to move
     * @param dir the direction in which to move
     * @return the new position
     */
    public Optional<Position> move(int amount, Direction dir);


}
