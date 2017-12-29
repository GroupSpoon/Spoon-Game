package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;
import edu.swarthmore.cs.spoon.model.interfaces.PlayerState;
import edu.swarthmore.cs.spoon.model.interfaces.StateType;

public class PlayerStateImpl implements PlayerState{

    protected PlayerCharacter pc;

    public PlayerStateImpl(PlayerCharacter pc){
        this.pc = pc;
    }

    @Override
    public StateType getState() {
        return null;
    }

    @Override
    public boolean timestep() {
        return true;
    }
}
