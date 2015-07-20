package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.component.CollisionGameComponent;
import com.kristianfischer.projectzero.game.Game;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.attributes.Hitbox;
import com.kristianfischer.projectzero.handler.ComponentHandler;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public class Player extends GameObject {

    public final static int RENDER_HEIGHT = 32;
    public final static int RENDER_WIDTH = 32;

    private boolean mPlayerFired;
    private double mFireDelayTime;
    private double mFireTimer;
    private boolean mIsDead;

    public static class Builder extends AbstractBuilder<Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        public Player build() {
            return new Player( this );
        }
    }

    protected Player(Builder builder) {
        super(builder);
        ComponentHandler.getInstance().initialize( this );
        mFireDelayTime = Game.NUMBER_OF_TICKS * .5;
        mFireTimer = mFireDelayTime;
        mPlayerFired = false;
    }

    @Override
    public void tick() {
        ComponentHandler.getInstance().update( this );
        performFire();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPosition, yPosition, RENDER_WIDTH, RENDER_HEIGHT);
    }

    public void fire() {
        mPlayerFired = true;
    }

    public void ceaseFire() {
        mPlayerFired = false;
    }

    private void performFire() {
        if( mFireTimer == mFireDelayTime && mPlayerFired ) {
            Laser projectile = new Laser.Builder()
                    .xPosition(xPosition + RENDER_WIDTH /2)
                    .yPosition(yPosition + RENDER_HEIGHT /2)
                    .gameId(GameId.PLAYER_PROJECTILE)
                    .speed(5)
                    .isActive(true)
                    .collisionComponent(new CollisionGameComponent())
                    .build();
            projectile.getCollisionComponent().setHitbox(new Hitbox.Builder(projectile)
                    .rectangle(new Rectangle(projectile.getxPosition(),
                    projectile.getyPosition(), Laser.RENDER_WIDTH, Laser.RENDER_HEIGHT))
                    .build());
            projectile.setyVelocity( projectile.getSpeed() );
            DynamicGameObjectHandler.getInstance().addNewGameObject(projectile);
            mFireTimer = 0;
        }
        if( mFireTimer < mFireDelayTime ) {
            mFireTimer++;
        }
    }

    public void setIsDead( boolean isDead ) {
        mIsDead = isDead;
    }

    public boolean isDead() {
        return isDead();
    }


}
