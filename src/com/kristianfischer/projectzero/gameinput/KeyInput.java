package com.kristianfischer.projectzero.gameinput;

import com.kristianfischer.projectzero.game.GameHandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public class KeyInput extends KeyAdapter {

    private GameHandler mGameHandler;

    public KeyInput(GameHandler handler) {
        mGameHandler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //super.keyPressed(e);
        int key = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //super.keyReleased(e);
        int key = e.getKeyCode();
    }
}
