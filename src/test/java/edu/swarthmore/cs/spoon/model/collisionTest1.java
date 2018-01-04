package edu.swarthmore.cs.spoon.model;

import edu.swarthmore.cs.spoon.common.model.PointImpl;
import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.model.implementations.ThingImpl;
import edu.swarthmore.cs.spoon.common.model.ThingListenersHolderImpl;
import edu.swarthmore.cs.spoon.model.implementations.WorldImpl;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import static edu.swarthmore.cs.spoon.model.interfaces.Direction.*;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class collisionTest1 {

    World testWorld = new WorldImpl();
    Map<Integer, Location> locMap = testWorld.getLocationMap();
    Location loc = locMap.values().iterator().next();


    PlayerCharacter bob = testWorld.createCharacter("bob", 1);

    PlayerCharacter linda = testWorld.createCharacter("linda", 2);
    Point ul1 = new PointImpl(100,100);
    Point ur1 = new PointImpl(131,100);
    Point ll1 = new PointImpl(100,131);
    Point lr1 = new PointImpl(131,131);
    Position oldPos = new PositionImpl(ul1, ur1, ll1, lr1);

    Point ulD = new PointImpl(100,102);
    Point urD = new PointImpl(131,102);
    Point llD = new PointImpl(100,133);
    Point lrD = new PointImpl(131,133);
    Position newPosD = new PositionImpl(ulD, urD, llD, lrD);

    Point ul3 = new PointImpl(100,133);
    Point ur3 = new PointImpl(131,133);

    Point ll3 = new PointImpl(100,164);
    Point lr3 = new PointImpl(131,164);
    Position downTurkeyBurgerPos = new PositionImpl(ul3, ur3, ll3, lr3);

    Point ulU = new PointImpl(100,98);
    Point urU = new PointImpl(131,98);
    Point llU = new PointImpl(100,129);
    Point lrU = new PointImpl(131,129);
    Position newPosU = new PositionImpl(ulU, urU, llU, lrU);

    Point ul4 = new PointImpl(100,67);
    Point ur4 = new PointImpl(131,67);
    Point ll4 = new PointImpl(100,98);
    Point lr4 = new PointImpl(131,98);
    Position upTurkeyBurgerPos = new PositionImpl(ul4, ur4, ll4, lr4);

    Point ul5 = new PointImpl(67,100);
    Point ur5 = new PointImpl(98,100);
    Point ll5 = new PointImpl(67,131);
    Point lr5 = new PointImpl(98,131);
    Position leftTurkeyBurgerPos = new PositionImpl(ul5, ur5, ll5, lr5);

    Point ulL = new PointImpl(98,100);
    Point urL = new PointImpl(129,100);
    Point llL = new PointImpl(98,131);
    Point lrL = new PointImpl(129,131);
    Position newPosL = new PositionImpl(ulL, urL, llL, lrL);

    Point ul6 = new PointImpl(133,100);
    Point ur6 = new PointImpl(164,100);
    Point ll6 = new PointImpl(133,131);
    Point lr6 = new PointImpl(164,131);
    Position rightTurkeyBurgerPos = new PositionImpl(ul6, ur6, ll6, lr6);

    Point ulR = new PointImpl(102,100);
    Point urR = new PointImpl(133,100);
    Point llR = new PointImpl(102,131);
    Point lrR = new PointImpl(133,131);
    Position newPosR = new PositionImpl(ulR, urR, llR, lrR);


    Direction downMovState = DOWN;
    Direction upMovState = UP;
    Direction leftMovState = LEFT;
    Direction rightMovState = RIGHT;

    Thing turkeyBurger = new ThingImpl("turkeyBurger", loc,  oldPos, new ThingListenersHolderImpl(), ThingType.ITEM);


    @Before
    public void makeThingsTransparent(){
        for(Thing thing : testWorld.getThings().values()){
            thing.setSolidity(false);
        }
    }

    @Test
    public void testCollisionDown() {
        bob.setLocation(loc);
        bob.setPosition(oldPos);

        loc.addThing(turkeyBurger);
        turkeyBurger.setLocation(loc);
        turkeyBurger.setPosition(downTurkeyBurgerPos);
        //Position oldPos =bob.getPosition();
        bob.setMovState(downMovState);
        testWorld.timestep();
        testWorld.timestep();
        testWorld.timestep();
        List<Integer> newPointValsList = new ArrayList<Integer>();
        List<Integer> bobPointValsList = new ArrayList<Integer>();

        List<Point> newPoints = newPosD.getPoints();
        List<Point> bobPoints = bob.getPosition().getPoints();
        for(int i = 0; i<4; i++){
            newPointValsList.add(newPoints.get(i).getX());
            newPointValsList.add(newPoints.get(i).getY());
            bobPointValsList.add(bobPoints.get(i).getX());
            bobPointValsList.add(bobPoints.get(i).getY());
        }
        assertEquals(newPointValsList, bobPointValsList);

    }

    @Test
    public void testCollisionUp() {
        bob.setPosition(oldPos);
        bob.setLocation(loc);
        loc.addThing(turkeyBurger);
        turkeyBurger.setLocation(loc);
        turkeyBurger.setPosition(upTurkeyBurgerPos);
        //Position oldPos =bob.getPosition();
        bob.setMovState(upMovState);
        testWorld.timestep();
        testWorld.timestep();
        testWorld.timestep();
        List<Integer> newPointValsList = new ArrayList<Integer>();
        List<Integer> bobPointValsList = new ArrayList<Integer>();
        List<Point> newPoints = newPosU.getPoints();
        List<Point> bobPoints = bob.getPosition().getPoints();
        for(int i = 0; i<4; i++){
            newPointValsList.add(newPoints.get(i).getX());
            newPointValsList.add(newPoints.get(i).getY());
            bobPointValsList.add(bobPoints.get(i).getX());
            bobPointValsList.add(bobPoints.get(i).getY());
        }
        assertEquals(newPointValsList, bobPointValsList);

    }

    @Test
    public void testCollisionLeft() {
        bob.setPosition(oldPos);
        bob.setLocation(loc);
        loc.addThing(turkeyBurger);
        turkeyBurger.setLocation(loc);
        turkeyBurger.setPosition(leftTurkeyBurgerPos);
        //Position oldPos =bob.getPosition();
        bob.setMovState(leftMovState);
        testWorld.timestep();
        testWorld.timestep();
        testWorld.timestep();
        List<Integer> newPointValsList = new ArrayList<Integer>();
        List<Integer> bobPointValsList = new ArrayList<Integer>();
        List<Point> newPoints = newPosL.getPoints();
        List<Point> bobPoints = bob.getPosition().getPoints();
        for(int i = 0; i<4; i++){
            newPointValsList.add(newPoints.get(i).getX());
            newPointValsList.add(newPoints.get(i).getY());
            bobPointValsList.add(bobPoints.get(i).getX());
            bobPointValsList.add(bobPoints.get(i).getY());
        }
        assertEquals(newPointValsList, bobPointValsList);

    }

    @Test
    public void testCollisionRight() {
        bob.setPosition(oldPos);
        bob.setLocation(loc);


        loc.addThing(turkeyBurger);
        turkeyBurger.setLocation(loc);
        turkeyBurger.setPosition(rightTurkeyBurgerPos);
        //Position oldPos =bob.getPosition();
        bob.setMovState(rightMovState);
        testWorld.timestep();
        testWorld.timestep();
        testWorld.timestep();
        List<Integer> newPointValsList = new ArrayList<Integer>();
        List<Integer> bobPointValsList = new ArrayList<Integer>();
        List<Point> newPoints = newPosR.getPoints();
        List<Point> bobPoints = bob.getPosition().getPoints();
        for(int i = 0; i<4; i++){
            newPointValsList.add(newPoints.get(i).getX());
            newPointValsList.add(newPoints.get(i).getY());
            bobPointValsList.add(bobPoints.get(i).getX());
            bobPointValsList.add(bobPoints.get(i).getY());
        }
        assertEquals(newPointValsList, bobPointValsList);

    }


}

