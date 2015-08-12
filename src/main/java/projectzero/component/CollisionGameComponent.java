package main.java.projectzero.component;

import main.java.projectzero.gameobject.GameObject;
import main.java.projectzero.gameobject.attributes.Hitbox;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/16/15.
 */
public class CollisionGameComponent extends GameComponent {

    private GameObject mGameObject;
    private Hitbox mHitbox;

    public CollisionGameComponent() {
    }

    @Override
    public void initialize(GameObject gameObject) {
        mGameObject = gameObject;
    }

    @Override
    public void tick() {
        if( mHitbox != null ) mHitbox.udpate();
    }

    @Override
    public void render(Graphics g) throws IllegalStateException {
        mHitbox.render(g);
    }

    public void setHitbox( Hitbox hitbox ) {
        mHitbox = hitbox;
    }

    public Hitbox getHitbox( ) {
        return mHitbox;
    }
}