

package edu.swarthmore.cs.spoon.model;

import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.model.implementations.ThingImpl;
import edu.swarthmore.cs.spoon.common.model.ThingListenersHolderImpl;
import edu.swarthmore.cs.spoon.model.implementations.WorldImpl;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import static edu.swarthmore.cs.spoon.model.interfaces.Direction.*;
import static org.junit.Assert.assertEquals;

    public class twoPCsMovingTests {

        World testWorld = new WorldImpl();
        Map<Integer, Location> locMap = testWorld.getLocationMap();
        Location loc = locMap.values().iterator().next();

        PlayerCharacter bob = testWorld.createCharacter("bob", 1);
        PlayerCharacter linda = testWorld.createCharacter("linda", 2);
        Position bobPos1 = new PositionImpl(69 /*NICE*/, 100, 32, 32);
        Position lindaPos1 = new PositionImpl( 101, 100, 32, 32);
        Direction bobMovState = RIGHT;
        Direction lindaMovState = LEFT;
        Position turkeyBurgerPos = new PositionImpl(85, 100, 32, 1);

        @Before
        public void setUp(){
            bob.setLocation(loc);
            bob.setPosition(bobPos1);

            bob.setMovState(bobMovState);
            linda.setLocation(loc);
            linda.setPosition(lindaPos1);

            linda.setMovState(lindaMovState);
        }

        @Test
        public void twoPCsOccupySameSpace(){
            for(int i = 0; i<16; i++) {
                testWorld.timestep();
            }
            assertEquals(bob.getPosition().getPointsAsInts(), linda.getPosition().getPointsAsInts());

        }

        @Test
        public void twoPCsCollideWithThing(){
            Thing turkeyBurger = new ThingImpl("turkeyBurger", loc, turkeyBurgerPos, new ThingListenersHolderImpl(), ThingType.ITEM);
            loc.addThing(turkeyBurger);
            //burger.setLocation(loc);
            //burger.setPosition(burgerPos);
            for(int i = 0; i<16; i++){
                testWorld.timestep();
            }
            
        }

    }
