package com.kristianfischer.projectzero.handler;

import com.kristianfischer.projectzero.gameobject.GameObject;

/**
 * Created by kristianhfischer on 7/20/15.
 */
public class ComponentHandler {
    private static ComponentHandler INSTANCE = new ComponentHandler();
    public static ComponentHandler getInstance() {
        return INSTANCE;
    }

    private ComponentHandler() {
    }

    public void initialize( GameObject object ) {
        if( object.getMovementComponent() != null )     object.getMovementComponent().initialize( object );
        if( object.getCollisionComponent() != null )    object.getCollisionComponent().initialize( object );

    }

    public void update( GameObject object ) {
        if( object.getMovementComponent() != null)      object.getMovementComponent().update();
        if( object.getCollisionComponent() != null )    object.getCollisionComponent().update();
    }
}
