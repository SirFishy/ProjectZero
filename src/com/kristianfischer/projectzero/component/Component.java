package com.kristianfischer.projectzero.component;

import com.kristianfischer.projectzero.gameobject.GameObject;

/**
 * Created by kristianhfischer on 7/18/15.
 */
public abstract class Component {

    public abstract void initialize( GameObject gameObject );
    public abstract void update( ) throws IllegalStateException;

}
