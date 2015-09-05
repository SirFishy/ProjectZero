package main.java.projectzero.component;

import main.java.projectzero.gameobject.GameObject;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/18/15.
 * IGameComponent is a contract for common behavior across all GameObjects that can be added to an object
 * if it is required
 */
public interface IGameComponent {

    /**
     * initialize should be called by the GameObject constructor for each IGameComponent it has added
     * @param gameObject
     */
    void initialize( GameObject gameObject );

    /**
     * tick should be called by the GameObject tick method to update IGameComponent behavior
     * @throws IllegalStateException if IGameComponent was not properly initialized
     */
    void tick() throws IllegalStateException;

    /**
     * render should be called by the GameObject render method to draw any required images or objects associated
     * with the GameComponents behavior
     * @param g - the canvas that is being drawn on
     * @throws IllegalStateException if IGameComponent was not properly initialized
     */
    void render(Graphics g) throws IllegalStateException;

}
