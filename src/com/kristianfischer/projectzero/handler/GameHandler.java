package com.kristianfischer.projectzero.handler;

import com.kristianfischer.projectzero.gameobject.GameObject;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public class GameHandler  {

    public LinkedList<GameObject> gameObjects = new LinkedList<>();

    public void tick() {
        for( GameObject object : gameObjects ) {
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
}
