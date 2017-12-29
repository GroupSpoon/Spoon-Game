package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.model.interfaces.Location;
import edu.swarthmore.cs.spoon.model.interfaces.Position;
import edu.swarthmore.cs.spoon.model.interfaces.ThingListenersHolder;
import edu.swarthmore.cs.spoon.model.interfaces.ThingType;

public class ItemImpl extends ThingImpl{
    public ItemImpl(String name, Location loc, Position pos, ThingListenersHolder holder) {
        super(name, loc, pos, holder, ThingType.ITEM);

    }



}
