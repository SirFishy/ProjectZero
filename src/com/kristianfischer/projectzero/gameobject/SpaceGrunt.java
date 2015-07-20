package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.artificalbehavior.SpaceGruntBehavior;
import com.kristianfischer.projectzero.handler.ComponentHandler;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/20/15.
 */
public class SpaceGrunt extends GameObject {

    public final static int RENDER_HEIGHT = 32;
    public final static int RENDER_WIDTH = 32;
    private SpaceGruntBehavior behavior;

    public static class Builder extends AbstractBuilder<Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        public SpaceGrunt build() {
            return new SpaceGrunt(this);
        }
    }

    public SpaceGrunt(AbstractBuilder builder) {
        super(builder);
        ComponentHandler.getInstance().initialize(this);
        behavior = new SpaceGruntBehavior(this);
    }

    @Override
    public void tick() {
        ComponentHandler.getInstance().update(this);
        behavior.update();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPosition, yPosition, RENDER_WIDTH, RENDER_HEIGHT);
    }
}
