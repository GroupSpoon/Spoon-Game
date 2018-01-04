package edu.swarthmore.cs.spoon.model;

import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.common.model.ThingListenersHolderImpl;
import edu.swarthmore.cs.spoon.model.implementations.ActionImpl;
import edu.swarthmore.cs.spoon.model.implementations.WorldImpl;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static edu.swarthmore.cs.spoon.model.interfaces.Direction.LEFT;
import static edu.swarthmore.cs.spoon.model.interfaces.Direction.RIGHT;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class actionTests1 {


    WorldImpl testWorld = new WorldImpl();
    Map<Integer, Location> locMap = testWorld.getLocationMap();
    Location loc = locMap.values().iterator().next();

    PlayerCharacter bob = testWorld.createCharacter("bob", 1);
    //PlayerCharacter linda = testWorld.createCharacter("linda", 2);

    Position bobPos1 = new PositionImpl(300, 100, 32, 32);
    //Position lindaPos1 = new PositionImpl( 101, 100, 32, 32);

    Position turkeyBurgerPos = new PositionImpl(85, 100, 32, 1);
    Thing turkeyBurger = testWorld.createThing("turkeyBurger", loc, turkeyBurgerPos, null);
    Action pushTurkBurg;
    Action lookAt;
    Action nudgeRight;
    Thing maybeTeleportato;
    Action teleport;




    @Before
    public void makeAction(){

        List<String> endMsg = new ArrayList<>();
        endMsg.add("The TURKEY burger slides across the floor like a hockey puck. Good job, frien");
        pushTurkBurg = new ActionImpl("push that turkeyBurger", turkeyBurger, 0, endMsg, 2);


        List<String> lookAtMsg = new ArrayList<>();
        lookAtMsg.add("it's a burger. I know it looks like a rectangle, though.");

         lookAt = new ActionImpl("lookAt",  turkeyBurger, 0, lookAtMsg, 0);

        List<String> endNudgeMsg = new ArrayList<>();
        endMsg.add("it nudged... or maybe it didn't? this message prints the same regardless of whether it worked lol");

         nudgeRight = new ActionImpl("nudge right", turkeyBurger, 0, endNudgeMsg, 1);

        Map<Integer, Thing> things = testWorld.getThings();
        for(Thing thing : things.values()){
            if(thing.getName() == "teleportato"){
                maybeTeleportato = thing;
            }
        }
        Map<Integer, Action> actions = maybeTeleportato.getActions();
        for(Action action : actions.values()){
            if(action.getActionName() == "teleport"){
                teleport = action;
            }
        }



    }

    @Test
    public void testTakingAction(){
        bob.initiateAction(pushTurkBurg, new ActionEndListener() {
            @Override
            public void actionEnded(int actionId) {

            }
        });
        testWorld.timestep();
        testWorld.timestep();
        testWorld.timestep();
        assertEquals(turkeyBurger.getPosition().getPointsAsInts(), new PositionImpl(10, 10, 20, 10).getPointsAsInts());

    }


    @Test
    public void testStateChangeForActions(){
        StateType beforeState = bob.getPlayerState();
        bob.initiateAction(pushTurkBurg, new ActionEndListener() {
            @Override
            public void actionEnded(int actionId) {

            }
        });
        StateType duringState = bob.getPlayerState();
        assertNotEquals(beforeState, duringState);
    }

    @Test
    public void testStateChangeAfterAction(){
        bob.initiateAction(pushTurkBurg, new ActionEndListener() {
            @Override
            public void actionEnded(int actionId) {

            }
        });
        StateType duringState = bob.getPlayerState();
        testWorld.timestep();
        testWorld.timestep();
        testWorld.timestep();
        StateType afterState = bob.getPlayerState();
        assertNotEquals(duringState, afterState);
    }

    @Test
    public void testStateIsActingDuringAction(){
        bob.initiateAction(pushTurkBurg, new ActionEndListener() {
            @Override
            public void actionEnded(int actionId) {

            }
        });
        StateType duringState = bob.getPlayerState();
        assertEquals(duringState, StateType.ACTING);

    }

    @Test
    public void testStateReturnsToIdleAfterAction(){
        StateType[] expectedArray = new StateType[]{StateType.IDLE, StateType.ACTING, StateType.IDLE};
        StateType beforeState = bob.getPlayerState();
        bob.initiateAction(pushTurkBurg, new ActionEndListener() {
            @Override
            public void actionEnded(int actionId) {

            }
        });
        StateType duringState = bob.getPlayerState();
        testWorld.timestep();
        testWorld.timestep();
        testWorld.timestep();
        StateType afterState = bob.getPlayerState();
        StateType[] actualArray = new StateType[]{beforeState, duringState, afterState};
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testExamine(){
        turkeyBurger.addAction(pushTurkBurg);
        turkeyBurger.addAction(lookAt);
        turkeyBurger.addAction(nudgeRight);
        Collection<Action> actions = new ArrayList<>();

        actions.add(nudgeRight);
        actions.add(lookAt);
        actions.add(pushTurkBurg);



        assertEquals(turkeyBurger.examine(bob).values().size(), actions.size());

    }

    @Test
    public void testTeleport(){

        Position oldPos = bob.getPosition();
        System.out.println(oldPos.getPointsAsInts());
        bob.initiateAction(teleport, new ActionEndListener() {
            @Override
            public void actionEnded(int actionId) {

            }
        });
        testWorld.timestep();
        testWorld.timestep();
        testWorld.timestep();
        Position newPos = bob.getPosition();
        assertEquals(new PositionImpl(oldPos.getULX(), oldPos.getULY()-50, oldPos.getHeight(), oldPos.getWidth()).getPointsAsInts(), newPos.getPointsAsInts());
    }







}
