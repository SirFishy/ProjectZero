package com.kristianfischer.projectzero.gameinput;

import com.kristianfischer.projectzero.command.Command;
import com.kristianfischer.projectzero.handler.GameHandler;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.GameObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public class KeyInput extends KeyAdapter {

    private GameHandler mGameHandler;
    private KeyMapper mKeyMapper;

    public KeyInput(GameHandler handler, KeyMapper mapper) {
        mGameHandler = handler;
        mKeyMapper = mapper;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //super.keyPressed(e);
        int key = e.getKeyCode();
        Iterator<GameObject> iterator = mGameHandler.getGameObjectIterator();
        while( iterator.hasNext() ) {
            GameObject object = iterator.next();
            if( object.getGameId() == GameId.PLAYER) {
                //System.out.println("Key pressed: " + e.getKeyChar());
                handlePlayerKeyPressed(key, object);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //super.keyReleased(e);
        int key = e.getKeyCode();
        Iterator<GameObject> iterator = mGameHandler.getGameObjectIterator();
        while( iterator.hasNext() ) {
            GameObject object = iterator.next();
            if( object.getGameId() == GameId.PLAYER) {
                //System.out.println("Key released: " + e.getKeyChar());
                handlePlayerKeyReleased(key, object);
            }
        }
    }

    private void handlePlayerKeyPressed(int key, GameObject object) {
        Command command = mKeyMapper.getCommand(key);
        if( command != null) command.execute(object);
    }

    private void handlePlayerKeyReleased(int key, GameObject object) {

        Command command = mKeyMapper.getCommand(key);
        if( command != null) command.stop(object);

    }
}
