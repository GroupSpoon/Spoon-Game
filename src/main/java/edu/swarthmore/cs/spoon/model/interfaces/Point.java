package edu.swarthmore.cs.spoon.model.interfaces;


/**contains the X and Y coordinates for a single point
 **/
public interface Point {

    /** returns X co-ordinate of point**/
    public int getX();
    /** returns y co-ordinate of point**/
    public int getY();
    /** sets X co-ordinate of point
     * @param x an int that we want the y coord to be
     * @return true if changed successfully, false if not
     * **/
    //The below methods have been taken out because Point is immutable now.
    //I have preserved them here because I am a pack rat
   // public boolean setX(int x);
    /** sets Y co-ordinate of point
     * @param y an int that we want the y coord to be
     * @return true if changed successfully, false if not
     * **/
    //public boolean setY(int y);

    /**
     * modifies the x,y coords of the point by the specified values
     * @param x the amount to add to the current x
     * @param y the amount to add to the current y
     * @return the new point
     */
    //public Point move(int x, int y);



}
