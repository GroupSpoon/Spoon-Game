package edu.swarthmore.cs.spoon.common;

import java.util.concurrent.SynchronousQueue;

public class Wait {
    private boolean ready = false;
    public synchronized void ready(){
        this.ready = true;
        this.notifyAll();
    }
    public synchronized void pause(){
        if (ready) {
            return;
        }
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
