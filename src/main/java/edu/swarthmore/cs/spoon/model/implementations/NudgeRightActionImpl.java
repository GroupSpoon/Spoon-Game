package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.model.interfaces.Position;
import edu.swarthmore.cs.spoon.model.interfaces.Thing;

import java.util.List;
/*
This action makes the target move 1 grid point to the right
If it is right up against the rightmost border of a location, it will do nothing
 */
public class NudgeRightActionImpl extends ActionImpl{
    public NudgeRightActionImpl(String name, Thing target, int cost, List<String> endMsg, int dur) {
        super(name, target, cost,endMsg, dur);
    }

    @Override
    public void startAction() {
        Position oldPos = target.getPosition();
        target.setPosition(new PositionImpl(oldPos.getULY(), oldPos.getULX()+1, oldPos.getHeight(), oldPos.getWidth()));
    }
}
