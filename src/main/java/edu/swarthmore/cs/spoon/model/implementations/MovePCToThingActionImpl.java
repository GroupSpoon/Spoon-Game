package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.model.interfaces.Position;
import edu.swarthmore.cs.spoon.model.interfaces.Thing;

import java.util.List;
/*
Actions of this sort are for moving the player onto the target
 */
public class MovePCToThingActionImpl extends ActionImpl{
    private Position targetPos;
    public MovePCToThingActionImpl(String name, Thing target, int cost, List<String> endMsg, String desc, int dur) {
        super(name, target, cost, endMsg, desc, dur);
         targetPos = target.getPosition();

    }

    public MovePCToThingActionImpl(String name, Thing target, int cost, List<String> endMsg, int dur) {
        super(name, target, cost, endMsg, dur);
        targetPos = target.getPosition();

    }


    @Override
    public void startAction() {
        Position oldPos = actor.getPosition();
        actor.setPosition(new PositionImpl(targetPos.getULX(), targetPos.getULY(), oldPos.getHeight(), oldPos.getWidth()));
    }
}
