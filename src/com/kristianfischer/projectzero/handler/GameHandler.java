package com.kristianfischer.projectzero.handler;

import com.kristianfischer.projectzero.gameobject.GameObject;

import java.awt.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public class GameHandler  {

    private static final GameHandler INSTANCE = new GameHandler();
    private ConcurrentLinkedQueue<GameObject> gameObjects;
    private GameHandler() { gameObjects = new ConcurrentLinkedQueue<>(); }

    public static GameHandler getInstance() { return INSTANCE; }

    public void tick() {
        Iterator<GameObject> iterator = gameObjects.iterator();
        while( iterator.hasNext() ) {
            GameObject object = iterator.next();
            object.tick();
        }

    }

    public void render(Graphics g) {
        for( GameObject object : gameObjects ) {
            object.render(g);
        }
    }

    public void addGameObject( GameObject gameObject ) {
        gameObjects.add(gameObject);
    }

    public void removeGameObject( GameObject gameObject ) {
        gameObjects.remove(gameObject);
    }

    public Iterator<GameObject> getGameObjectIterator() {
        return gameObjects.iterator();
    }
}
