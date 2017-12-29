package edu.swarthmore.cs.spoon.common.model;

import edu.swarthmore.cs.spoon.model.interfaces.ActionEndListener;
import edu.swarthmore.cs.spoon.model.interfaces.ActionListenersHolder;

import java.util.ArrayList;
import java.util.List;

public class ActionListenersHolderImpl implements ActionListenersHolder {
    private List<ActionEndListener> actionEndListenersList = new ArrayList<>();
    @Override
    public void addActionEndListener(ActionEndListener listener) {
        actionEndListenersList.add(listener);
    }

    @Override
    public boolean removeActionEndListener(ActionEndListener listener) {
        return actionEndListenersList.remove(listener);
    }

    @Override
    public List<ActionEndListener> getAllListeners() {
        return actionEndListenersList;
    }
}
