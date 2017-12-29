package edu.swarthmore.cs.spoon.common;

public class WaitableCounter {
    private int counter = 0;
    public synchronized void increment(){
        counter++;
        this.notifyAll();
    }
    public synchronized void waitFor(int goal){
        while (counter<goal) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                //do nothing
            }
        }
    }
}
