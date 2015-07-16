package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameinput.KeyMapper;
import com.kristianfischer.projectzero.handler.DynamicObjectHandler;
import com.kristianfischer.projectzero.state.PlayerMovementHandler;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public class Player extends GameObject {

    private PlayerMovementHandler mPlayerMovementHandler;
    public final static int HEIGHT = 32;
    public final static int WIDTH = 32;

    public Player(int xPosition, int yPosition, GameId id) {
        super(xPosition, yPosition, id);
        speed = 5;
        mPlayerMovementHandler = new PlayerMovementHandler(this);
    }

    @Override
    public void tick() {
        xPosition += xVelocity;
        yPosition += yVelocity;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPosition, yPosition, WIDTH, HEIGHT);
    }

    public boolean isMoving() {
        return ( xVelocity != 0 ) || ( yVelocity != 0 );
    }

    public PlayerMovementHandler getPlayerMovementHandler() {
        return mPlayerMovementHandler;
    }

    public void fire() {
        Projectile projectile = new Projectile( xPosition + WIDTH/2,
                yPosition + HEIGHT/2, GameId.Projectile);
        projectile.setyVelocity( projectile.getSpeed() );
        DynamicObjectHandler.getInstance().addNewGameObject(projectile);
    }

}
