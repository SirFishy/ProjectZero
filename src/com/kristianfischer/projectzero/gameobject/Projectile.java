package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.game.Game;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.game.GameWindow;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/14/15.
 */
public class Projectile extends GameObject {

    public static class Builder extends AbstractBuilder<Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        public Projectile build() {
            return new Projectile(this);
        }
    }

    public Projectile(Builder builder) {
        super(builder);
    }

    @Override
    public void tick() {
        yPosition += yVelocity;
        if( yPosition < 0 || yPosition > Game.HEIGHT ) {
            System.out.println("Destorying Projectile");
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPosition, yPosition, 5, 10);
    }
}
