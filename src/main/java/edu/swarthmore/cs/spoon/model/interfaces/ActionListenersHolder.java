package edu.swarthmore.cs.spoon.model.interfaces;

import java.util.List;

public interface ActionListenersHolder {
    public void addActionEndListener(ActionEndListener listener);

    public boolean removeActionEndListener(ActionEndListener listener);

    public List<ActionEndListener> getAllListeners();
}
