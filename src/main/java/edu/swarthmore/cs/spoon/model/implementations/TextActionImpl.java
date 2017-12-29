package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.model.interfaces.Thing;

import java.util.List;
/*
This action displays text.
It has no mechanical effect.
 */
public class TextActionImpl extends ActionImpl {
    public TextActionImpl(String name, Thing target, int cost, List<String> endMsg, String desc, int dur) {
        super(name, target, cost, endMsg, desc, dur);
    }

    public TextActionImpl(String name, Thing target, int cost, List<String> endMsg, int dur) {
        super(name, target, cost, endMsg, dur);
    }

    @Override
    public void startAction() {
        //do nothing
    }
}
