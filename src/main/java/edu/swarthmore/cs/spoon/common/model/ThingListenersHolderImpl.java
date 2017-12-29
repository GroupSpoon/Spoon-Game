package edu.swarthmore.cs.spoon.common.model;

import edu.swarthmore.cs.spoon.model.interfaces.NewThingListener;
import edu.swarthmore.cs.spoon.model.interfaces.ThingListenersHolder;

import java.util.ArrayList;
import java.util.List;

public class ThingListenersHolderImpl implements ThingListenersHolder {
    private List<NewThingListener> newThingListenerList = new ArrayList<>();


    @Override
    public void addNewThingListener(NewThingListener listener) {
        this.newThingListenerList.add(listener);
    }

    @Override
    public boolean removeNewThingListener(NewThingListener listener) {
        return this.newThingListenerList.remove(listener);
    }

    @Override
    public List<NewThingListener> getAllListeners() {
        return newThingListenerList;
    }
}
