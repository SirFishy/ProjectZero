package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.game.Game;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;
import com.kristianfischer.projectzero.handler.MovementHandler;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public class Player extends GameObject {

    public final static int HEIGHT = 32;
    public final static int WIDTH = 32;

    private MovementHandler mPlayerMovementHandler;
    private boolean mPlayerFired;
    private double mFireDelayTime;
    private double mFireTimer;

    public static class Builder extends AbstractBuilder<Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }

    protected Player(Builder builder) {
        super(builder);
        mPlayerMovementHandler = new MovementHandler(this);
        mFireDelayTime = Game.NUMBER_OF_TICKS * .5;
        mFireTimer = mFireDelayTime;
        mPlayerFired = false;
    }

    @Override
    public void tick() {
        movePlayer();
        performFire();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPosition, yPosition, WIDTH, HEIGHT);
    }

    public boolean isMoving() {
        return ( xVelocity != 0 ) || ( yVelocity != 0 );
    }

    public MovementHandler getPlayerMovementHandler() {
        return mPlayerMovementHandler;
    }

    public void fire() {
        mPlayerFired = true;
    }

    public void ceaseFire() {
        mPlayerFired = false;
    }

    private void performFire() {
        if( mFireTimer == mFireDelayTime && mPlayerFired ) {
            Projectile projectile = new Projectile.Builder()
                    .xPosition(xPosition + WIDTH/2)
                    .yPosition(yPosition + HEIGHT/2)
                    .gameId(GameId.Projectile)
                    .speed(5)
                    .build();
            projectile.setyVelocity( projectile.getSpeed() );
            DynamicGameObjectHandler.getInstance().addNewGameObject(projectile);
            mFireTimer = 0;
        }
        if( mFireTimer < mFireDelayTime ) {
            mFireTimer++;
        }
    }

    private void movePlayer() {
        xPosition += xVelocity;
        yPosition += yVelocity;
    }



}
