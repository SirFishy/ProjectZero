package main.java.projectzero.gameobject;

import main.java.projectzero.game.Game;
import main.java.projectzero.game.GameId;
import main.java.projectzero.handler.ComponentHandler;
import main.java.projectzero.handler.DynamicGameObjectHandler;
import main.java.projectzero.spawner.LaserSpawner;

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
        if( isDestroyed ) {
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(this);
            return;
        }
        ComponentHandler.getInstance().tick(this);
        performFire();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPosition, yPosition, width, height);
        ComponentHandler.getInstance().render(g, this);
    }

    public void fire() {
        mPlayerFired = true;
    }

    public void ceaseFire() {
        mPlayerFired = false;
    }

    private void performFire() {
        if( mFireTimer == mFireDelayTime && mPlayerFired ) {
            Laser laser = LaserSpawner.SpawnLaser(xPosition + width / 2,
                    yPosition + height / 2, GameId.PLAYER_PROJECTILE);
            DynamicGameObjectHandler.getInstance().addNewGameObject(laser);
            mFireTimer = 0;
        }
        if( mFireTimer < mFireDelayTime ) {
            mFireTimer++;
        }
    }

}
