package main.java.projectzero.handler;

import main.java.projectzero.component.GameComponent;
import main.java.projectzero.gameobject.GameObject;

import java.awt.*;

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

    public void tick(GameObject object) {
        for( GameComponent component : object.getGameComponents() ) {
            component.tick();
        }
    }

    public void render(Graphics g, GameObject object ) {
        for( GameComponent component : object.getGameComponents() ) {
            component.render(g);
        }
    }
}
