package main.java.projectzero.artificalbehavior;

import main.java.projectzero.gameobject.GameObject;

/**
 * Created by kristianhfischer on 7/20/15.
 */
public abstract class Behavior {

    protected GameObject gameObject;

    public Behavior( GameObject gameObject ) {
        this.gameObject = gameObject;
    }
    public abstract void tick();
}
