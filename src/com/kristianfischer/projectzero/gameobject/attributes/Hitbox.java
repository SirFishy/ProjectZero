package com.kristianfischer.projectzero.gameobject.attributes;

import com.kristianfischer.projectzero.gameobject.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristianhfischer on 7/16/15.
 * TODO: Need to update this to handle polygons, research required
 */
public class Hitbox {
    protected GameObject gameObject;
    protected List<Rectangle> hitboxShapes;
    protected boolean isCollisionDetected;

    public static class Builder {
        private List<Rectangle> hitboxShapes = new ArrayList<>();
        private GameObject gameObject;
        private boolean isCollisionDetected;

        public Builder(GameObject object ) {
            gameObject = object;
            isCollisionDetected = false;
        }

        public Builder rectangle( Rectangle rectangle ) {
            hitboxShapes.add(rectangle);
            return this;
        }

        public Hitbox build() {
            return new Hitbox( this );
        }
    }

    public void udpate() {
        for ( Rectangle rectangle : hitboxShapes ) {
            rectangle.setLocation( (int) rectangle.getX() + gameObject.getxVelocity(),
                    (int) rectangle.getY() + gameObject.getyVelocity() );
            //System.out.println("Hitbox location: " + rectangle.getX() + ", " + rectangle.getY());
            //System.out.println("Object location: " + gameObject.getxPosition() + ", " + gameObject.getyPosition() );
        }
    }

    public Hitbox(Builder builder) {
        this.hitboxShapes = builder.hitboxShapes;
        this.gameObject = builder.gameObject;
        this.isCollisionDetected = builder.isCollisionDetected;
    }

    public boolean detectCollision(GameObject object) throws IllegalStateException {
        if( object.getCollisionComponent() == null ) throw new IllegalStateException("GameObject does not have collision component");
        if( object.getCollisionComponent().getHitbox() == null ) throw new IllegalStateException("GameObject does not have hitbox");
        Hitbox hitbox = object.getCollisionComponent().getHitbox();
        for( Rectangle myRectangle : hitboxShapes) {
            for( Rectangle otherRectangle : hitbox.hitboxShapes) {
                if( myRectangle.intersects( otherRectangle) )
                    return true;
            }
        }
        return false;
    }
}
