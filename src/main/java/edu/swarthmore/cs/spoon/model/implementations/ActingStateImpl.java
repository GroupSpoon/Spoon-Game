package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.model.interfaces.Action;
import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;
import edu.swarthmore.cs.spoon.model.interfaces.StateType;

import static edu.swarthmore.cs.spoon.model.interfaces.StateType.ACTING;

public class ActingStateImpl extends PlayerStateImpl{

    private int actionDur;
    private Action action;
    private boolean canDoSpirit;
//TODO add listeners
    public ActingStateImpl(Action action, PlayerCharacter pc){
        super(pc);
        this.action = action;
        this.action.getTarget().setInUse(true);
        if (action.canPerformAction()) {
            canDoSpirit = true;
            this.action.startAction();
            this.actionDur = action.getActionDuration();
        }
        else {
            canDoSpirit = false;
            actionDur = 0;
        }
    }

    @Override
    public StateType getState() {
        return ACTING;
    }

    public Action getAction(){
        return this.action;
    }

    public int getActionDur(){
        return this.actionDur;
    }


    @Override
    public boolean timestep() {

        //System.out.println(actionDur);
        if(actionDur == 0){
            action.notifyListeners(canDoSpirit);
            //action.startAction();
            //gotta call some listeners
            this.action.getTarget().setInUse(false);
            return true;

        }
        actionDur -= 1;
        return false;
    }
}
