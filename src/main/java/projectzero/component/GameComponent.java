package main.java.projectzero.component;

import main.java.projectzero.gameobject.GameObject;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/18/15.
 */
public abstract class GameComponent {

    public abstract void initialize( GameObject gameObject );
    public abstract void tick() throws IllegalStateException;
    public abstract void render(Graphics g) throws IllegalStateException;

}
