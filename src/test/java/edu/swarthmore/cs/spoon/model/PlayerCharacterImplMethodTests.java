

package edu.swarthmore.cs.spoon.model;

import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.model.implementations.WorldImpl;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import static edu.swarthmore.cs.spoon.model.interfaces.Direction.*;
import static org.junit.Assert.assertEquals;

public class PlayerCharacterImplMethodTests {

    WorldImpl testWorld = new WorldImpl();
    Map<Integer, Location> locMap = testWorld.getLocationMap();
    Location loc = locMap.values().iterator().next();
    PlayerCharacter bob = testWorld.createCharacter("bob", 1);
    PlayerCharacter linda = testWorld.createCharacter("linda", 2);
    Position bobPos1 = new PositionImpl(100 /*NICE*/, 100, 32, 32);
    Position lindaPos1 = new PositionImpl( 101, 100, 32, 32);
    Direction bobMovState = RIGHT;
    Direction lindaMovState = LEFT;
    Direction newMovState = DOWN;
    Position burgerPos = new PositionImpl(85, 100, 32, 1);

    @Before
    public void setUp(){
        bob.setPosition(bobPos1);
        bob.setLocation(loc);
        bob.setMovState(bobMovState);
        linda.setPosition(lindaPos1);
        linda.setLocation(loc);
        linda.setMovState(lindaMovState);
    }

    @Test
    public void testGetName(){

        assertEquals("bob", bob.getName());

    }

    @Test
    public void testGetId(){
        assertEquals(1, bob.getId());
    }



    @Test
    public void testGetMovState(){
        assertEquals(RIGHT, bob.getMovState());
    }

    @Test
    public void testSetMovState(){
        bob.setMovState(newMovState);
        assertEquals(newMovState, bob.getMovState());
    }

    @Test
    public void testSetAndGetLocation(){
        Location newLoc = testWorld.createNewLoc("newLoc", 1023, 1023);
        bob.setLocation(newLoc);
        assertEquals(newLoc, bob.getLocation());
    }

//    @Test
//    public void testSetAndGetPosition(){
//        Position newPos = new PositionImpl(700, 700, 32,32);
//
//    }




}
