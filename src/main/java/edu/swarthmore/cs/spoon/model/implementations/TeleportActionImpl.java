package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.model.interfaces.Position;
import edu.swarthmore.cs.spoon.model.interfaces.Thing;

import java.util.List;

public class TeleportActionImpl extends ActionImpl{
    public TeleportActionImpl(String name, Thing target, int cost,List<String> endMsg, int dur) {
        super(name, target, cost, endMsg, dur);
    }

    @Override
    public void startAction() {
        Position oldPos = actor.getPosition();
        actor.setPosition(new PositionImpl(oldPos.getULX(), oldPos.getULY()-50, oldPos.getHeight(), oldPos.getWidth()));
    }
}
