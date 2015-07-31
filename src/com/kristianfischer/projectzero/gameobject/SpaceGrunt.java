package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.artificalbehavior.Behavior;
import com.kristianfischer.projectzero.artificalbehavior.SpaceGruntBehavior;
import com.kristianfischer.projectzero.handler.ComponentHandler;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;
import com.kristianfischer.projectzero.level.LevelHud;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/20/15.
 */
public class SpaceGrunt extends GameObject {

    private Behavior behavior;
    private int mScore;

    public static class Builder extends AbstractBuilder<Builder> {
        private int mScore = 0;

        @Override
        protected Builder self() {
            return this;
        }

        public Builder score(int score) {
            mScore = score;
            return this;
        }

        public SpaceGrunt build() {
            return new SpaceGrunt(this);
        }
    }

    public SpaceGrunt(Builder builder) {
        super(builder);
        ComponentHandler.getInstance().initialize(this);
        mScore = builder.mScore;
        behavior = new SpaceGruntBehavior(this);
    }

    @Override
    public void tick() {
        if( isDestroyed ) {
            LevelHud.getInstance().addScore(mScore);
            behavior.update();
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(this);
            return;
        }
        ComponentHandler.getInstance().update(this);
        behavior.update();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPosition, yPosition, width, height);
        collisionComponent.getHitbox().render(g);
    }

    public Behavior getBehavior() {
        return behavior;
    }
}
