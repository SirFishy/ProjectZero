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
    public static boolean DEBUG_HITBOX = true;
    protected GameObject gameObject;
    protected List<HitboxParameters> hitboxShapes;

    public static class Builder {
        private List<HitboxParameters> hitboxShapes = new ArrayList<>();
        private GameObject gameObject;


        public Builder(GameObject object ) {
            gameObject = object;
        }

        public Builder rectangle( int xOffset, int yOffset, int width, int height ) {
            hitboxShapes.add(new HitboxParameters(xOffset, yOffset, width, height));
            return this;
        }

        public Hitbox build() {
            return new Hitbox( this );
        }
    }

    public void udpate() {
        //System.out.println("Hitbox location: " + rectangle.getX() + ", " + rectangle.getY());
        //System.out.println("Object location: " + gameObject.getxPosition() + ", " + gameObject.getyPosition() );
    }

    public void render(Graphics g) {
        if( DEBUG_HITBOX ) {
            for( HitboxParameters parameters : hitboxShapes) {
                g.setColor(Color.blue);
                g.drawRect(gameObject.getxPosition() + parameters.getxOffset(),
                        gameObject.getyPosition() + parameters.getyOffset(),
                        parameters.getWidth(),
                        parameters.getHeight());
            }

        }
    }

    public Hitbox(Builder builder) {
        this.hitboxShapes = builder.hitboxShapes;
        this.gameObject = builder.gameObject;
    }

    public boolean detectCollision(GameObject otherObject) throws IllegalStateException {
        if( otherObject.getCollisionComponent() == null ) throw new IllegalStateException("GameObject does not have collision component");
        if( otherObject.getCollisionComponent().getHitbox() == null ) throw new IllegalStateException("GameObject does not have hitbox");
        if( !otherObject.isActive() || otherObject.isDestroyed() ) return false;
        Hitbox otherHitbox = otherObject.getCollisionComponent().getHitbox();
        for( HitboxParameters myParameters : hitboxShapes) {
            for( HitboxParameters otherParameters : otherHitbox.hitboxShapes) {
                Rectangle myRectangle = createRectangle(gameObject.getxPosition(),
                        gameObject.getyPosition(), myParameters);
                Rectangle otherRectangle = createRectangle(otherObject.getxPosition(),
                        otherObject.getyPosition(), otherParameters);
                if( myRectangle.intersects(otherRectangle)) return true;
            }
        }
        return false;
    }

    private Rectangle createRectangle( int xPos, int yPos, HitboxParameters parameters ) {
        return new Rectangle(
                parameters.getxOffset() + xPos,
                parameters.getyOffset() + yPos,
                parameters.getWidth(),
                parameters.getHeight());
    }
}
