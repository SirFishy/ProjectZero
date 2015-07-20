package com.kristianfischer.projectzero.handler;

import com.kristianfischer.projectzero.component.GameComponent;
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
        for(GameComponent component : object.getGameComponents() ) {
            component.initialize( object );
        }
    }

    public void update( GameObject object ) {
        for( GameComponent component : object.getGameComponents() ) {
            component.update();
        }
    }
}
