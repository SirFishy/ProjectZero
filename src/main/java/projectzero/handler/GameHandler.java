package main.java.projectzero.handler;

import main.java.projectzero.game.GameId;
import main.java.projectzero.gameobject.GameObject;

import java.awt.*;
import java.util.Iterator;
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
            System.out.println("Added Player");
            players.add(gameObject);
        }
        if( gameObject.getGameId().equals( GameId.ENEMY ) ||
                gameObject.getGameId().equals(GameId.UFO)) {
            System.out.println("Added Enemy");
            enemies.add(gameObject);
        }
        if( gameObject.getGameId().equals(GameId.PLAYER_PROJECTILE)) {
            System.out.println("Added Player Projectile");
            projectiles.add(gameObject);
            mNumberOfPlayerProjectiles++;
        }
        if(gameObject.getGameId().equals(GameId.ENEMY_PROJECTILE)) {
            System.out.println("Added Enemy Projectile");
            projectiles.add(gameObject);
            mNumberOfEnemyProjectiles++;
        }
        if( gameObject.getGameId().equals(GameId.BUNKER) ) {
            System.out.println("Added Bunker");
            bunkers.add(gameObject);
        }
    }

    public void removeGameObject( GameObject gameObject ) {
        gameObjects.remove(gameObject);
        if( gameObject.getGameId().equals( GameId.PLAYER )) {
            players.remove(gameObject);
        }
        if( gameObject.getGameId().equals( GameId.ENEMY ) ||
                gameObject.getGameId().equals(GameId.UFO)) {
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
