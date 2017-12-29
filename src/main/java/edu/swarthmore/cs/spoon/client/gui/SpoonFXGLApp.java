package edu.swarthmore.cs.spoon.client.gui;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.settings.GameSettings;
public class SpoonFXGLApp extends GameApplication{

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
    }
}
