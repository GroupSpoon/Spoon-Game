package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.model.interfaces.Thing;

import java.util.List;
/*
The Look At action should give the player who takes it a text box with a description of the
target. This description should appear as the end message for this action.

This action should not have a start message
This action should not have anything in its performAction method
This action should not have a spoon cost
This action should have (always?) have a duration of 0
 */
public class LookAtActionImpl extends ActionImpl{

    public LookAtActionImpl(String name, Thing target, List<String> endMsg) {
        super(name, target, 0, endMsg, 0);
    }

    @Override
    public void startAction() {
        //do nothing
    }
}
