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

    public void handleCollisions( GameHandler gameHandler ) {
        Iterator<GameObject> players;
        Iterator<GameObject> projectiles;
        for( players = gameHandler.getPlayerIterator(); players.hasNext(); ) {
            Player player = (Player) players.next();
            for( projectiles = gameHandler.getProjectileIterator(); projectiles.hasNext(); ) {
                GameObject projectile = projectiles.next();
                if( projectile.getGameId().equals(GameId.ENEMY_PROJECTILE)) {
                    if( player.getCollisionComponent().getHitbox().detectCollision(projectile) )
                        System.out.println("Player collided with enemy projectile");
                }
            }
        }

        Iterator<GameObject> enemies;

        for( enemies = gameHandler.getEnemyIterator(); enemies.hasNext(); ) {
            GameObject enemy = enemies.next();
            for( projectiles = gameHandler.getProjectileIterator(); projectiles.hasNext(); ) {
                GameObject projectile = projectiles.next();
                if( projectile.getGameId().equals(GameId.PLAYER_PROJECTILE)) {
                    if( enemy.getCollisionComponent().getHitbox().detectCollision(projectile) ) {
                        System.out.println("Enemy collided with player projectile");
                        enemy.setIsDestroyed(true);
                        projectile.setIsActive(false);
                        projectile.setIsDestroyed(true);
                    }
                }
            }
        }


    }



}
