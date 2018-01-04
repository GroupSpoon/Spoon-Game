package edu.swarthmore.cs.spoon.network;

import edu.swarthmore.cs.spoon.client.network.GameClientImpl;
import edu.swarthmore.cs.spoon.client.network.interfaces.GameClient;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("You need one argument: the ip address of the server you want to connect to");
            //return;
        }
        try {
            GameClient client = new GameClientImpl("127.0.0.1");
        }
        catch (IOException e) {
            System.out.println("Hmm, couldn't get you on the server. " +
                    "That's kinda odd. I'd tell you why, but I'm not 100% " +
                    "on what a sever IS.");
        }
    }
}
