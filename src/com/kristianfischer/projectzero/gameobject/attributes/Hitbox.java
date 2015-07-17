package com.kristianfischer.projectzero.gameobject.attributes;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/16/15.
 */
public class Hitbox {
    protected Rectangle rectangleHitbox;

    public Hitbox(int width, int height) {
        rectangleHitbox = new Rectangle(width, height);
    }

    public boolean detectCollision(Hitbox hitbox) {
        return this.rectangleHitbox.intersects(hitbox.rectangleHitbox);
    }
}
