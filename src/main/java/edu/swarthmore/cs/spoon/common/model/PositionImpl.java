package edu.swarthmore.cs.spoon.common.model;

import edu.swarthmore.cs.spoon.model.interfaces.Direction;
import edu.swarthmore.cs.spoon.model.interfaces.Point;
import edu.swarthmore.cs.spoon.model.interfaces.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TODO: make the return statements of the setters meaningful
public class PositionImpl implements Position {
    private int ulX;
    private int ulY;
    private int width;
    private int height;
    private Point upLeft;
    private Point upRight;
    private Point lowLeft;
    private Point lowRight;
    private List<Point> pointList = new ArrayList<>();
    //private no argument constructor to trick kryonet into letting me send these over the network
    private PositionImpl(){}

    public PositionImpl(Point ul, Point ur, Point ll, Point lr){
        upLeft = ul;

        upRight = ur;
        lowLeft = ll;

        lowRight = lr;
        ulX = ul.getX();
        ulY = ul.getY();
        height = ll.getY()-ul.getY();
        width = ur.getX()-ul.getX();

        pointList.add(upLeft);
        pointList.add(upRight);
        pointList.add(lowLeft);
        pointList.add(lowRight);
    }

    /**
     * Alternate constructor for PositionImpl that takes in an x and a y CORRESPONDING TO THE UPPER LEFT CORNER OF THE THING
     * and a height and a width
     * @param x
     * @param y
     * @param height
     * @param width
     */
    public PositionImpl(int x, int y, int height, int width){

        upLeft = new PointImpl(x,y);
        upRight = new PointImpl(x + width, y);
        lowLeft = new PointImpl(x, y + height);
        lowRight = new PointImpl(x + width, y + height);
        ulX = x;
        ulY = y;
        this.height = height;
        this.width = width;

        pointList.add(upLeft);
        pointList.add(upRight);
        pointList.add(lowLeft);
        pointList.add(lowRight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionImpl position = (PositionImpl) o;

        if (ulX != position.ulX) return false;
        if (ulY != position.ulY) return false;
        if (width != position.width) return false;
        if (height != position.height) return false;
        if (upLeft != null ? !upLeft.equals(position.upLeft) : position.upLeft != null) return false;
        if (upRight != null ? !upRight.equals(position.upRight) : position.upRight != null) return false;
        if (lowLeft != null ? !lowLeft.equals(position.lowLeft) : position.lowLeft != null) return false;
        if (lowRight != null ? !lowRight.equals(position.lowRight) : position.lowRight != null) return false;
        return pointList != null ? pointList.equals(position.pointList) : position.pointList == null;
    }

    @Override
    public int hashCode() {
        int result = ulX;
        result = 31 * result + ulY;
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + (upLeft != null ? upLeft.hashCode() : 0);
        result = 31 * result + (upRight != null ? upRight.hashCode() : 0);
        result = 31 * result + (lowLeft != null ? lowLeft.hashCode() : 0);
        result = 31 * result + (lowRight != null ? lowRight.hashCode() : 0);
        result = 31 * result + (pointList != null ? pointList.hashCode() : 0);
        return result;
    }

    /*
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PositionImpl position = (PositionImpl) o;

            if (upLeft != null ? !upLeft.equals(position.upLeft) : position.upLeft != null) return false;
            if (upRight != null ? !upRight.equals(position.upRight) : position.upRight != null) return false;
            if (lowLeft != null ? !lowLeft.equals(position.lowLeft) : position.lowLeft != null) return false;
            if (lowRight != null ? !lowRight.equals(position.lowRight) : position.lowRight != null) return false;
            return pointList != null ? pointList.equals(position.pointList) : position.pointList == null;
        }

        @Override
        public int hashCode() {
            int result = upLeft != null ? upLeft.hashCode() : 0;
            result = 31 * result + (upRight != null ? upRight.hashCode() : 0);
            result = 31 * result + (lowLeft != null ? lowLeft.hashCode() : 0);
            result = 31 * result + (lowRight != null ? lowRight.hashCode() : 0);
            result = 31 * result + (pointList != null ? pointList.hashCode() : 0);
            return result;
        }
    */
    @Override
    public List<Point> getPoints() {
        return pointList;
    }

    //A method to make tests less annoying to write.

    public List<Integer> getPointsAsInts() {
        List<Integer> intList = new ArrayList<>();
        for(Point pt : this.pointList){
            intList.add(pt.getX());
            intList.add(pt.getY());
        }
        return intList;
    }


    @Override
    public Point getUL() {
        return upLeft;
    }

//    @Override
//    public Boolean setUL(Point newUL) {
//        //Check that making newUL the new upper left corner will not give this position an invalid shape
//
//        upLeft = newUL;
//        return null;
//    }

    @Override
    public Point getUR() {
        return upRight;
    }

//    @Override
//    public Boolean setUR(Point newUR) {
//        upRight = newUR;
//        return null;
//    }

    @Override
    public Point getLL() {
        return lowLeft;
    }

//    @Override
//    public Boolean setLL(Point newLL) {
//        lowLeft = newLL;
//        return null;
//    }

    @Override
    public Point getLR() {
        return lowRight;
    }

//    @Override
//    public Boolean setLR(Point newLR) {
//        lowRight = newLR;
//        return null;
//    }
//
//    /**
//     * Makes sure that a newPoint would not make the position not a rectangle anymore
//     * @param newPoint the new point to check
//     * @param cornerNum an int from 0 to 3 indicating which corner the newPoint is supposed to be for. 0=upper left, 1=upper right, 2=lower left, 3 = lower right
//     * @return true if the newPoint doesn't make the position not a rectangle anymore
//     */
//    private Boolean isValid(Point newPoint, int cornerNum){
//        if(cornerNum == 0){
//            if(this.upRight.getX() <= newPoint.getX())
//        }
//        else if(cornerNum == 1){
//
//        }
//        else if(cornerNum == 2){
//
//        }
//        else if(cornerNum == 3){
//
//        }
//    }


    @Override
    public int getULX() {
        return this.ulX;
    }

    @Override
    public int getULY() {
        return this.ulY;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public Optional<Position> moveLeft(int amount) {

        Optional<Position> toReturn = Optional.empty();
        Position updatedPos;
        List<Point> updatedPointList = new ArrayList<>();

        for(Point pt : pointList){
            int prev = pt.getX();
            Point newPt = new PointImpl(prev-amount, pt.getY());
            updatedPointList.add(newPt);

        }
        updatedPos = new PositionImpl(updatedPointList.get(0), updatedPointList.get(1), updatedPointList.get(2), updatedPointList.get(3));

        toReturn = Optional.ofNullable(updatedPos);
        return toReturn;
    }

    @Override
    public Optional<Position> moveRight(int amount) {

        Optional<Position> toReturn = Optional.empty();
        Position updatedPos;
        List<Point> updatedPointList = new ArrayList<>();
        for(Point pt : pointList){
            int prev = pt.getX();
            Point newPt = new PointImpl(prev+amount, pt.getY());
            updatedPointList.add(newPt);
        }
        updatedPos = new PositionImpl(updatedPointList.get(0), updatedPointList.get(1), updatedPointList.get(2), updatedPointList.get(3));

        toReturn = Optional.ofNullable(updatedPos);
        return toReturn;
    }

    @Override
    public Optional<Position> moveUp(int amount) {

        Optional<Position> toReturn = Optional.empty();
        Position updatedPos;
        List<Point> updatedPointList = new ArrayList<>();
        for(Point pt : pointList){
            int prev = pt.getY();
            Point newPt = new PointImpl(pt.getX(), prev-amount);
            updatedPointList.add(newPt);
        }
        updatedPos = new PositionImpl(updatedPointList.get(0), updatedPointList.get(1), updatedPointList.get(2), updatedPointList.get(3));

        toReturn = Optional.ofNullable(updatedPos);
        return toReturn;
    }

    @Override
    public Optional<Position> moveDown(int amount) {

        Optional<Position> toReturn = Optional.empty();
        Position updatedPos;
        List<Point> updatedPointList = new ArrayList<>();
        for(Point pt : pointList){
            int prev = pt.getY();
            Point newPt = new PointImpl(pt.getX(), prev+amount);
            updatedPointList.add(newPt);
        }
        updatedPos = new PositionImpl(updatedPointList.get(0), updatedPointList.get(1), updatedPointList.get(2), updatedPointList.get(3));

        toReturn = Optional.ofNullable(updatedPos);
        return toReturn;
    }



    @Override
    public Optional<Position> move(int amount, Direction dir) {

        Optional<Position> toReturn = Optional.empty();
        Position updatedPos;
        List<Point> updatedPointList = new ArrayList<>();
        for(Point pt : pointList){
            int prev;
            Point newPt;
            if(dir == Direction.UP) {
                prev = pt.getY();
                newPt = new PointImpl(pt.getX(), prev - amount);
            }
            else if(dir == Direction.DOWN){
                prev = pt.getY();
                newPt = new PointImpl(pt.getX(), prev + amount);
            }
            else if(dir == Direction.LEFT){
                prev = pt.getX();
                newPt = new PointImpl(prev-amount, pt.getY());
            }
            else if(dir == Direction.RIGHT){
                prev = pt.getX();
                newPt = new PointImpl(prev+amount, pt.getY());
            }
            else {
                throw new IllegalStateException("Move 2 none????");
            }
            updatedPointList.add(newPt);
        }
        updatedPos = new PositionImpl(updatedPointList.get(0), updatedPointList.get(1), updatedPointList.get(2), updatedPointList.get(3));

        toReturn = Optional.ofNullable(updatedPos);
        return toReturn;
    }




}
