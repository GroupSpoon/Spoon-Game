package edu.swarthmore.cs.spoon.model;

import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.model.implementations.WorldImpl;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import org.junit.Test;

import java.util.Map;
import static edu.swarthmore.cs.spoon.model.interfaces.Direction.*;
import static org.junit.Assert.assertEquals;

public class invalidPositionTest {

    World testWorld = new WorldImpl();
    Map<Integer, Location> locMap = testWorld.getLocationMap();
    Location loc = locMap.values().iterator().next();
    PlayerCharacter bob = testWorld.createCharacter("bob", 1);
    Position oldPos = new PositionImpl(0, 0, 32, 32);

    Direction movState = UP;

    @Test
    public void tryToGoOutOfBounds(){
        bob.setPosition(oldPos);
        bob.setLocation(loc);
        bob.setMovState(UP);
        testWorld.timestep();
        assertEquals(oldPos.getPointsAsInts(), bob.getPosition().getPointsAsInts());

    }

}
