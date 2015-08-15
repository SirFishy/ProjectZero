package main.java.projectzero.component;

import main.java.projectzero.gameobject.GameObject;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/18/15.
 * GameComponent is a contract for common behavior across all GameObjects that can be added to an object
 * if it is required
 */
public abstract class GameComponent {

    /**
     * initialize should be called by the GameObject constructor for each GameComponent it has added
     * @param gameObject
     */
    public abstract void initialize( GameObject gameObject );

    /**
     * tick should be called by the GameObject tick method to update GameComponent behavior
     * @throws IllegalStateException if GameComponent was not properly initialized
     */
    public abstract void tick() throws IllegalStateException;

    /**
     * render should be called by the GameObject render method to draw any required images or objects associated
     * with the GameComponents behavior
     * @param g - the canvas that is being drawn on
     * @throws IllegalStateException if GameComponent was not properly initialized
     */
    public abstract void render(Graphics g) throws IllegalStateException;

}
