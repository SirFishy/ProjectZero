package main.java.projectzero.artificalbehavior;

import main.java.projectzero.gameobject.GameObject;

/**
 * Created by kristianhfischer on 7/20/15.
 * A contract for custom behavior if it is required by GameObjects
 */
public abstract class Behavior {

    protected GameObject gameObject;

    public Behavior( GameObject gameObject ) {
        this.gameObject = gameObject;
    }

    /**
     * tick is called by the GameObject's tick method. Any updates to the GameObject's
     * behavior should be implemented here.
     */
    public abstract void tick();
}
