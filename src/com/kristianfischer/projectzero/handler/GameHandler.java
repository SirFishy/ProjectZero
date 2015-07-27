package com.kristianfischer.projectzero.handler;

import com.kristianfischer.projectzero.game.GameId;
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
    private ConcurrentLinkedQueue<GameObject> players;
    private ConcurrentLinkedQueue<GameObject> enemies;
    private ConcurrentLinkedQueue<GameObject> projectiles;
    private ConcurrentLinkedQueue<GameObject> bunkers;
    private int mNumberOfPlayerProjectiles;
    private int mNumberOfEnemyProjectiles;
    private GameHandler() {
        gameObjects = new ConcurrentLinkedQueue<>();
        players = new ConcurrentLinkedQueue<>();
        enemies = new ConcurrentLinkedQueue<>();
        projectiles = new ConcurrentLinkedQueue<>();
        bunkers = new ConcurrentLinkedQueue<>();
    }

    public static GameHandler getInstance() { return INSTANCE; }

    public void tick() {
        Iterator<GameObject> iterator = gameObjects.iterator();
        while( iterator.hasNext() ) {
            GameObject object = iterator.next();
            object.tick();
        }

    }

    public void render(Graphics g) {
        Iterator<GameObject> iterator = gameObjects.iterator();
        while( iterator.hasNext() ) {
            GameObject object = iterator.next();
            object.render(g);
        }
    }

    public void addGameObject( GameObject gameObject ) {
        gameObjects.add(gameObject);
        if( gameObject.getGameId().equals( GameId.PLAYER )) {
            //System.out.println("Added Player");
            players.add(gameObject);
        }
        if( gameObject.getGameId().equals( GameId.ENEMY )) {
            //System.out.println("Added SpaceGrunt");
            enemies.add(gameObject);
        }
        if( gameObject.getGameId().equals(GameId.PLAYER_PROJECTILE)) {
            //System.out.println("Added Player Projectile");
            projectiles.add(gameObject);
            mNumberOfPlayerProjectiles++;
        }
        if(gameObject.getGameId().equals(GameId.ENEMY_PROJECTILE)) {
            //System.out.println("Added Enemy Projectile");
            projectiles.add(gameObject);
            mNumberOfEnemyProjectiles++;
        }
        if( gameObject.getGameId().equals(GameId.BUNKER) ) {
            //System.out.println("Added Bunker");
            bunkers.add(gameObject);
        }
    }

    public void removeGameObject( GameObject gameObject ) {
        gameObjects.remove(gameObject);
        if( gameObject.getGameId().equals( GameId.PLAYER )) {
            players.remove(gameObject);
        }
        if( gameObject.getGameId().equals( GameId.ENEMY )) {
            enemies.remove(gameObject);
        }
        if( gameObject.getGameId().equals(GameId.PLAYER_PROJECTILE) ) {
            mNumberOfPlayerProjectiles--;
            projectiles.remove(gameObject);
        }
        if( gameObject.getGameId().equals(GameId.ENEMY_PROJECTILE) ) {
            mNumberOfEnemyProjectiles--;
            projectiles.remove(gameObject);
        }
        if( gameObject.getGameId().equals(GameId.BUNKER) ) {
            bunkers.remove(gameObject);
        }
    }

    public Iterator<GameObject> getGameObjectIterator() {
        return gameObjects.iterator();
    }
    public Iterator<GameObject> getPlayerIterator() { return players.iterator(); }
    public Iterator<GameObject> getEnemyIterator() { return enemies.iterator(); }
    public Iterator<GameObject> getProjectileIterator() { return projectiles.iterator(); }
    public Iterator<GameObject> getBunkerIterator() { return bunkers.iterator(); }

    public int getNumberOfPlayerProjectiles() {
        return mNumberOfPlayerProjectiles;
    }

    public int getNumberOfEnemyProjectiles() {
        return mNumberOfEnemyProjectiles;
    }

}
