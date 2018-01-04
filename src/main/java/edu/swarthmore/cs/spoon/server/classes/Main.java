package edu.swarthmore.cs.spoon.server.classes;

import edu.swarthmore.cs.spoon.server.interfaces.GameManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        GameManager manager = new GameManagerImp();
        try {
            manager.startGame();
            manager.playGame();
        }
        catch (IOException e) {
            System.out.println("Hmm, couldn't get the server running. " +
                    "That's kinda odd. I'd tell you why, but I'm not 100% " +
                    "on what a sever IS.");
        }
    }
}
