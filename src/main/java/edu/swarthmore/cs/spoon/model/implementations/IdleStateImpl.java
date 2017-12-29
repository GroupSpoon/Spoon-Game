package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;
import edu.swarthmore.cs.spoon.model.interfaces.PlayerState;
import edu.swarthmore.cs.spoon.model.interfaces.StateType;

import static edu.swarthmore.cs.spoon.model.interfaces.StateType.IDLE;

public class IdleStateImpl extends PlayerStateImpl {

    public IdleStateImpl(PlayerCharacter pc){
        super(pc);
    }

    @Override
    public StateType getState() {
        return IDLE;
    }

    @Override
    public boolean timestep() {
        //Do nothing
        return false; //remain idle
    }
}
