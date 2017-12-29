package edu.swarthmore.cs.spoon.client.gui;

import javafx.application.Application;

//Different series of steps for getting world from server and launching that
//Both algs should produce world object.
//Get a class that takes a world and returns a scene
//Once you figure out what kind of world you're working on you return a scene
//You can have two JVFX applications which do make a world and then call the third classes make a scene


public class GameJavaFX {
    public static void main(String[] args) {
        //Application.launch(GameJVFXApp.class);
        Application.launch(ServerJVFXApp.class); //SERVER VERSION
        //Application.launch(BackendJVFXApp.class); //BACKEND VERSION
    }
}
