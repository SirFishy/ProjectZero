package main.java.projectzero.handler;

import main.java.projectzero.gameobject.attributes.IDamageable;
import main.java.projectzero.gameobject.attributes.IWeapon;
import main.java.projectzero.game.GameId;
import main.java.projectzero.gameobject.GameObject;
import main.java.projectzero.gameobject.Player;
import main.java.projectzero.level.LevelHud;

import java.util.Iterator;

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
                    if( player.getCollisionComponent() == null ) {
                        //System.out.println("Player does not have collision component");
                        break;
                    }
                    if( projectile.getCollisionComponent() == null ) {
                        //System.out.println("Projectile does not have collision component");
                        continue;
                    }
                    if( player.getCollisionComponent().getHitbox().detectCollision(projectile) ) {
                        projectile.setIsActive(false);
                        projectile.setIsDestroyed(true);
                        LevelHud.getInstance().removeLife();
                        //System.out.println("Player collided with enemy projectile");
                    }
                }
            }
        }

        Iterator<GameObject> enemies;

        for( enemies = gameHandler.getEnemyIterator(); enemies.hasNext(); ) {
            GameObject enemy = enemies.next();
            for( projectiles = gameHandler.getProjectileIterator(); projectiles.hasNext(); ) {
                GameObject projectile = projectiles.next();

                if( projectile.getCollisionComponent() == null ) {
                    //System.out.println("Projectile does not have collision component");
                    continue;
                }

                if( enemy.getCollisionComponent() == null ) {
                    //System.out.println("Enemy does not have collision component" );
                    break;
                }

                if( projectile.getGameId().equals(GameId.PLAYER_PROJECTILE)) {
                    if( enemy.getCollisionComponent().getHitbox().detectCollision(projectile) ) {
                        //System.out.println("Enemy collided with player projectile");
                        enemy.setIsDestroyed(true);
                        projectile.setIsActive(false);
                        projectile.setIsDestroyed(true);
                    }
                }
            }
        }

        Iterator<GameObject> bunkers;

        for( bunkers = gameHandler.getBunkerIterator(); bunkers.hasNext(); ) {
            GameObject bunker = bunkers.next();
            for( projectiles = gameHandler.getProjectileIterator(); projectiles.hasNext(); ) {
                GameObject projectile = projectiles.next();
                if( projectile.getGameId().equals(GameId.ENEMY_PROJECTILE) ||
                        projectile.getGameId().equals(GameId.PLAYER_PROJECTILE) ) {
                    if( bunker.getCollisionComponent() == null ) {
                        //System.out.println("Bunker does not have collision component");
                        break;
                    }
                    if( projectile.getCollisionComponent() == null ) {
                        //System.out.println("Projectile does not have collision component");
                        continue;
                    }
                    if( bunker.getCollisionComponent().getHitbox().detectCollision(projectile) ) {
                        //System.out.println("Bunker collided with projectile");
                        ((IWeapon) projectile).applyDamage((IDamageable) bunker);
                        projectile.setIsActive(false);
                        projectile.setIsDestroyed(true);
                    }
                }
            }

            for( enemies = gameHandler.getEnemyIterator(); enemies.hasNext(); ) {
                GameObject enemy = enemies.next();
                if( bunker.getCollisionComponent() == null ) {
                    //System.out.println("Bunker does not have collision component");
                    break;
                }
                if( enemy.getCollisionComponent() == null ) {
                    //System.out.println("Projectile does not have collision component");
                    continue;
                }
                if( bunker.getCollisionComponent().getHitbox().detectCollision(enemy) ) {
                    bunker.setIsActive(false);
                    bunker.setIsDestroyed(true);
                }
            }
        }


    }



}
