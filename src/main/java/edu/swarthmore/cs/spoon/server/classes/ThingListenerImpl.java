package edu.swarthmore.cs.spoon.server.classes;

import com.esotericsoftware.kryonet.Server;
import edu.swarthmore.cs.spoon.common.messages.Requests;
import edu.swarthmore.cs.spoon.model.interfaces.NewThingListener;
import edu.swarthmore.cs.spoon.model.interfaces.Thing;
import edu.swarthmore.cs.spoon.server.interfaces.GameManager;

public class ThingListenerImpl implements NewThingListener{
    Server server;
    GameManager manager;
    public ThingListenerImpl(Server server, GameManager manager) {
        this.server = server;
        this.manager = manager;
    }
    @Override
    public void newThingCreated(int thingId, Thing thing) {
        Requests.NewThing newThing = new Requests.NewThing();
        newThing.type = thing.getThingType();
        newThing.thingId = thingId;
        newThing.name = thing.getName();
        newThing.pos = thing.getPosition();
        newThing.locId = thing.getLocation().getLocationID();
        manager.addThingtoMap(thing);
        server.sendToAllTCP(newThing);
    }
}
