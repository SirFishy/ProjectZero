package com.kristianfischer.projectzero.component;

import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.gameobject.attributes.Hitbox;

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
    public void update() {
        if( mHitbox != null ) mHitbox.udpate();
    }

    public void setHitbox( Hitbox hitbox ) {
        mHitbox = hitbox;
    }

    public Hitbox getHitbox( ) {
        return mHitbox;
    }
}