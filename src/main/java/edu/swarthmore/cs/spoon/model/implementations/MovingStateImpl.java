package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.model.interfaces.Direction;
import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;
import edu.swarthmore.cs.spoon.model.interfaces.PlayerState;
import edu.swarthmore.cs.spoon.model.interfaces.StateType;

import static edu.swarthmore.cs.spoon.model.interfaces.StateType.MOVING;

public class MovingStateImpl extends PlayerStateImpl {
    private Direction currentDir;

    public MovingStateImpl(PlayerCharacter pc){
        super(pc);
    }


    @Override
    public StateType getState() {
        return MOVING;
    }

    @Override
    public boolean timestep() {
        this.pc.attemptStep();
        return (this.pc.getMovState()==Direction.NONE);
    }
}
