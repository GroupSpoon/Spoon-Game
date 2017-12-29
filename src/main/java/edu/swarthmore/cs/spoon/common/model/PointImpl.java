package edu.swarthmore.cs.spoon.common.model;

import edu.swarthmore.cs.spoon.model.interfaces.Point;

public class PointImpl implements Point {
    private int ptX;
    private int ptY;

    //private no argument constructor to trick kryonet into letting me send these over the network
    private PointImpl(){
    }
    public PointImpl(int x, int y){
        ptX = x;
        ptY = y;
    }

    @Override
    public int getX() {
        return ptX;
    }

    @Override
    public int getY() {
        return ptY;
    }
    /*
//TODO: make these returns meaningful
    @Override
    public boolean setX(int x) {
        ptX = x;
        return true;
    }

    @Override
    public boolean setY(int y) {
        ptY = y;

        return true;
    }

    @Override
    public Point move(int x, int y) {
        return null;
    }
    */
}
