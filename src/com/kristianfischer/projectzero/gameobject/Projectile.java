package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.game.GameId;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/14/15.
 */
public class Projectile extends GameObject {
    public Projectile(int xPosition, int yPosition, GameId id) {
        super(xPosition, yPosition, id);
        speed = 5;
    }

    @Override
    public void tick() {
        yPosition += yVelocity;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPosition, yPosition, 5, 10);
    }
}
