package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.artificalbehavior.Behavior;
import com.kristianfischer.projectzero.artificalbehavior.SpaceGruntBehavior;
import com.kristianfischer.projectzero.handler.ComponentHandler;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/20/15.
 */
public class SpaceGrunt extends GameObject {

    private Behavior behavior;

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
        g.fillRect(xPosition, yPosition, width, height);
    }
}
