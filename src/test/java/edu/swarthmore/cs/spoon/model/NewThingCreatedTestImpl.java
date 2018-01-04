package edu.swarthmore.cs.spoon.model;

import edu.swarthmore.cs.spoon.common.Wait;
import edu.swarthmore.cs.spoon.model.interfaces.NewThingListener;
import edu.swarthmore.cs.spoon.model.interfaces.Thing;

public class NewThingCreatedTestImpl implements NewThingListener{

    private Wait wait;

    public NewThingCreatedTestImpl(Wait wait){
        this.wait = wait;
    }


    @Override
    public void newThingCreated(int thingId, Thing thing) {
        this.wait.ready();
    }
}
