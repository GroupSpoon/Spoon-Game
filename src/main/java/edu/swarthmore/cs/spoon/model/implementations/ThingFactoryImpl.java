package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.model.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class ThingFactoryImpl implements ThingFactory{
    @Override
    public Thing createThing(String name, Location loc, Position pos, ThingListenersHolder holder, ThingType type, String lookAtText, boolean solid) {
        Thing thing = new ThingImpl(name, loc, pos, holder, type);
        List<String> lookAtMsg = new ArrayList<>();
        lookAtMsg.add(lookAtText);
        Action lookAtThing = new LookAtActionImpl("look at "+ name, thing, lookAtMsg);
        thing.addAction(lookAtThing);
        thing.setSolidity(solid);
        return thing;
    }
}
