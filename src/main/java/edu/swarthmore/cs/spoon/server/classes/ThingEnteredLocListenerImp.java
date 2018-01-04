package edu.swarthmore.cs.spoon.server.classes;

import com.esotericsoftware.kryonet.Server;
import edu.swarthmore.cs.spoon.model.interfaces.Thing;
import edu.swarthmore.cs.spoon.model.interfaces.ThingEnteredLocListener;

public class ThingEnteredLocListenerImp implements ThingEnteredLocListener{
    ThingEnteredLocListenerImp(Server server){}
    @Override
    public void ThingEntered(int locId, int thingId, Thing thing) {

    }
}
