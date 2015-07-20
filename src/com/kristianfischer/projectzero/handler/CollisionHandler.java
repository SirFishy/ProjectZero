package com.kristianfischer.projectzero.handler;

import com.kristianfischer.projectzero.game.Game;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.gameobject.Player;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by kristianhfischer on 7/18/15.
 */
public class CollisionHandler {
    private static CollisionHandler INSTANCE = new CollisionHandler();
    public static CollisionHandler getInstance() {
        return INSTANCE;
    }

    private GameHandler mGameHandler;

    public void handleCollisions( GameHandler gameHandler ) {
        Iterator<GameObject> players = gameHandler.getPlayerIterator();
        Iterator<GameObject> projectiles = gameHandler.getProjectileIterator();
        while( players.hasNext() ) {
            Player player = (Player) players.next();
            while( projectiles.hasNext() ) {
                GameObject projectile = projectiles.next();
                if( projectile.getGameId().equals(GameId.ENEMY_PROJECTILE)) {
                    System.out.println("Found enemy projectile");
                    if( player.getCollisionComponent().getHitbox().detectCollision(projectile) )
                        System.out.println("Player collided with enemy projectile");
                }
            }
        }

        Iterator<GameObject> enemies = gameHandler.getEnemyIterator();
        projectiles = gameHandler.getProjectileIterator();


    }



}
