package edu.swarthmore.cs.spoon.model;

import edu.swarthmore.cs.spoon.common.model.PointImpl;
import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.model.implementations.WorldImpl;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import static edu.swarthmore.cs.spoon.model.interfaces.Direction.*;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class pcMovementTestWithAlternatePositionConstructor {

    World testWorld = new WorldImpl();
    Map<Integer, Location> locMap = testWorld.getLocationMap();
    Location loc = locMap.values().iterator().next();
    PlayerCharacter bob = testWorld.createCharacter("bob", 1);

    PlayerCharacter linda = testWorld.createCharacter("linda", 2);
    Point ul1 = new PointImpl(0,0);
    Point ur1 = new PointImpl(31,0);
    Point ll1 = new PointImpl(0,31);
    Point lr1 = new PointImpl(31,31);
    Position oldPos = new PositionImpl(0, 0, 31, 31);

    Point ul2 = new PointImpl(0,1);
    Point ur2 = new PointImpl(31,1);
    Point ll2 = new PointImpl(0,32);
    Point lr2 = new PointImpl(31,32);
    Position newPos = new PositionImpl(0, 1, 31, 31);
    Direction movState = DOWN;

    @Test
    public void testMovementOfOnePlayer() {
        bob.setPosition(oldPos);
        bob.setLocation(loc);
        //Position oldPos =bob.getPosition();
        bob.setMovState(movState);
        testWorld.timestep();
        List<Integer> newPointValsList = new ArrayList<Integer>();
        List<Integer> bobPointValsList = new ArrayList<Integer>();
        List<Point> newPoints = newPos.getPoints();
        List<Point> bobPoints = bob.getPosition().getPoints();
        for(int i = 0; i<4; i++){
            newPointValsList.add(newPoints.get(i).getX());
            newPointValsList.add(newPoints.get(i).getY());
            bobPointValsList.add(bobPoints.get(i).getX());
            bobPointValsList.add(bobPoints.get(i).getY());
        }
        assertEquals(newPointValsList, bobPointValsList);

    }
/*
        @Test
        public void testMovementOfTwoPlayers(){
            Position oldPos =bob.getPosition();
            bob.setMovState(movState);
            testWorld.timestep();
            List<Integer> newPointValsList = new ArrayList<Integer>();
            List<Integer> bobPointValsList = new ArrayList<Integer>();
            List<Point> newPoints = newPos.getPoints();
            List<Point> bobPoints = bob.getPosition().getPoints();
            for(int i = 0; i<4; i++){
                newPointValsList.add(newPoints.get(i).getX());
                newPointValsList.add(newPoints.get(i).getY());
                bobPointValsList.add(bobPoints.get(i).getX());
                bobPointValsList.add(bobPoints.get(i).getY());
            }
            assertEquals(newPointValsList, bobPointValsList);
        }*/


}

