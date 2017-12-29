package edu.swarthmore.cs.spoon.model.interfaces;

public interface PlayerState {
    /**
     * This method should get called once for every playercharacter for every timestep of the world
     * It might do very different things depending on the implementation of PlayerState
     * IdleState should do nothing or not very much
     * MoveState should call attemptStep
     * ActState should update the duration of the action and make other actiony things happen
     * @return true if the state should change back to idle at the end of this timestep, false if we are still in the current state
     */
    public boolean timestep();


    /**
     * getter for what type of state this state is
     * @return the type of state this state is
     */
    public StateType getState();
}
