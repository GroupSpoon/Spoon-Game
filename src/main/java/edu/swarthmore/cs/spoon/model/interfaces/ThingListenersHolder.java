package edu.swarthmore.cs.spoon.model.interfaces;

import java.util.List;

public interface ThingListenersHolder {
    public void addNewThingListener(NewThingListener listener);

    public boolean removeNewThingListener(NewThingListener listener);

    public List<NewThingListener> getAllListeners();
}
