package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.game.Game;
import com.kristianfischer.projectzero.handler.ComponentHandler;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/14/15.
 */
public class Laser extends GameObject {

    public final static int RENDER_HEIGHT = 10;
    public final static int RENDER_WIDTH = 5;

    public static class Builder extends AbstractBuilder<Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        public Laser build() {
            return new Laser(this);
        }
    }

    public Laser(Builder builder) {
        super(builder);
        ComponentHandler.getInstance().initialize(this);
    }

    @Override
    public void tick() {
        yPosition += yVelocity;
        if( yPosition < 0 || yPosition > Game.HEIGHT ) {
            System.out.println("Destorying Laser");
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(this);
        }
        ComponentHandler.getInstance().update(this);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPosition, yPosition, RENDER_WIDTH, RENDER_HEIGHT);
    }
}
