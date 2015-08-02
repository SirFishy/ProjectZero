package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.artificalbehavior.Behavior;
import com.kristianfischer.projectzero.artificalbehavior.UfoBehavior;
import com.kristianfischer.projectzero.handler.ComponentHandler;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;
import com.kristianfischer.projectzero.level.LevelHud;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/31/15.
 */
public class Ufo extends GameObject {
    private int mScore;
    private Behavior mBehavior;

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

        public Ufo build() {
            return new Ufo(this);
        }
    }

    public Ufo(Builder builder) {
        super(builder);
        this.mScore = builder.mScore;
        ComponentHandler.getInstance().initialize(this);
        mBehavior = new UfoBehavior(this);
        this.movementComponent.setClamp(false);
    }

    @Override
    public void tick() {
        if( isDestroyed ) {
            LevelHud.getInstance().addScore(mScore);
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(this);
        }
        if( !isActive ) {
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(this);
        }
        ComponentHandler.getInstance().update(this);
        mBehavior.update();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(xPosition, yPosition, width, height);
        collisionComponent.getHitbox().render(g);
    }
}
