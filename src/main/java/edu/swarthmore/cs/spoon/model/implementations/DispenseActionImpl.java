package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.model.interfaces.Position;
import edu.swarthmore.cs.spoon.model.interfaces.Thing;

import java.util.List;

public class DispenseActionImpl extends ActionImpl{
    private Thing toDispense;
    public DispenseActionImpl(String name, Thing target, int cost, List<String> endMsg, int dur, Thing toDispense) {
        super(name, target, cost, endMsg, dur);
        this.toDispense = toDispense;
    }

    @Override
    public void startAction() {
        Position targetPos = target.getPosition();
        this.toDispense.setPosition(new PositionImpl(targetPos.getULY()+20, targetPos.getULX()+20, 20, 10));
    }




}
