package main.java.projectzero.artificalbehavior;

/**
 * Created by kristianhfischer on 7/22/15.
 * This interface is implemented by all enemies in the main enemy fleet. Each enemy that implements this behavior
 * will be controlled by the HiveHandler
 */
public interface IHiveUnderling {
    /**
     * prepareDescent is called by the HiveHandler to allow the enemy hive to "progress" towards the game over line.
     */
    void prepareDescent();

    /**
     * fireProjectile is called by the HiveHandler to allow the enemy to fire it's equipped projectile
     */
    void fireProjectile();

    /**
     * hasReachedBoundary is called by the HiveHandler to determine if any members of the hive have reached the left
     * or right side of the game window.
     * @return true if enemy has reached left or right side of screen. Otherwise, false
     */
    boolean hasReachedBoundary();
}
