package com.kristianfischer.projectzero.artificalbehavior;

import com.kristianfischer.projectzero.gameobject.GameObject;

/**
 * Created by kristianhfischer on 7/20/15.
 */
public abstract class Behavior {

    protected GameObject gameObject;

    public Behavior( GameObject gameObject ) {
        this.gameObject = gameObject;
    }
    public abstract void update();
}
