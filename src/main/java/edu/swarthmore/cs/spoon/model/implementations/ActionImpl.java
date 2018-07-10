package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.model.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class  ActionImpl implements Action{
    protected int actionId = IDManager.assignId();
    protected String actionName;
    protected PlayerCharacter actor = null;
    protected Thing target;
    protected int cost;
    protected List<String> actionDesc; //The description of the action
    protected List<String> startMsg;
    protected List<String> endMsg;
    protected int duration;
    private boolean hasDescription = false;
    private String description;
    private List<ActionEndListener> listeners = new ArrayList<>();

    public ActionImpl(String name, Thing target, int cost, List<String> endMsg, String description, int dur){
        this(name, target, cost, endMsg, dur);
        this.hasDescription = true;
        this.description = description;

    }

    public ActionImpl(String name, Thing target, int cost, List<String> endMsg, int dur){
        this.actionName = name;
        this.target = target;
        this.cost = cost;
        this.duration = dur;
        this.endMsg = endMsg;

    }

    @Override
    public void setActor(PlayerCharacter actor) {
        this.actor = actor;
    }

    @Override
    public void addEndListener(ActionEndListener listener) {
        listeners.add(listener);
    }

    @Override
    public String getActionDescription() {
        if (hasDescription) {
            return description;
        }
        return "";
    }

    @Override
    public boolean hasDescription() {
        return hasDescription;
    }

    @Override
    public void notifyListeners(boolean success) {
        for (ActionEndListener listener:listeners) {
            listener.actionEnded(this.actionId, success);
        }
        listeners.clear();
    }

    @Override
    public void removeEndListener(ActionEndListener listener) {
        listeners.remove(listener);
    }

    @Override
    public int getActionDuration() {
        return this.duration;
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }

    @Override
    public int getActionSpoonCost() {
        return this.cost;
    }

    @Override
    public List<String> getActionStatusEffects() {
        return null;
    }


    @Override
    public boolean canPerformAction() {
        if(!(this.actor == null)) {
            return this.actor.getSpoons() >= -(this.getActionSpoonCost());
        }
        return false;
    }

    @Override
    public void setSpoonCost(int num) {
        this.cost = num;

    }

    @Override
    public boolean canGetHelpForAction() {
        return false;
    }


    @Override
    public List<String> endActionMessage() {
        return this.endMsg;
    }


    @Override
    public void startAction() {
        target.setPosition(new PositionImpl(10, 10, 20, 10));

    }

    @Override
    public int getActionId() {
        return this.actionId;
    }

    @Override
    public Thing getTarget() {
        return this.target;
    }

    @Override
    public PlayerCharacter getActor() {
        return this.actor;
    }


}
