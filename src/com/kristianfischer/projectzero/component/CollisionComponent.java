package com.kristianfischer.projectzero.component;

import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.gameobject.attributes.Hitbox;

/**
 * Created by kristianhfischer on 7/16/15.
 */
public class CollisionComponent {

    private Hitbox mHitbox;

    public CollisionComponent( Hitbox hitbox ) {
        mHitbox = hitbox;
    }
}