package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.component.CollisionGameComponent;
import com.kristianfischer.projectzero.component.MovementGameComponent;
import com.kristianfischer.projectzero.game.Game;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.attributes.Hitbox;
import com.kristianfischer.projectzero.handler.ComponentHandler;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;
import com.kristianfischer.projectzero.spawner.LaserSpawner;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public class Player extends GameObject {

    private boolean mPlayerFired;
    private double mFireDelayTime;
    private double mFireTimer;

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
        g.fillRect(xPosition, yPosition, width, height);
        collisionComponent.getHitbox().render(g);
    }

    public void fire() {
        mPlayerFired = true;
    }

    public void ceaseFire() {
        mPlayerFired = false;
    }

    private void performFire() {
        if( mFireTimer == mFireDelayTime && mPlayerFired ) {
            Laser laser = LaserSpawner.SpawnLaser(xPosition + width /2,
                    yPosition + height / 2, GameId.PLAYER_PROJECTILE);
            DynamicGameObjectHandler.getInstance().addNewGameObject(laser);
            mFireTimer = 0;
        }
        if( mFireTimer < mFireDelayTime ) {
            mFireTimer++;
        }
    }

}
