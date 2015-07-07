package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.game.GameId;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public class Player extends GameObject {

    public Player(int xPosition, int yPosition, GameId id) {
        super(xPosition, yPosition, id);
        xVelocity += 1;
    }

    @Override
    public void tick() {
        xPosition += xVelocity;
        yPosition += yVelocity;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPosition, yPosition, 32, 32);
    }
}
