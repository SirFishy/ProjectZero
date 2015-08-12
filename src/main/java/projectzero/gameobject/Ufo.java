package main.java.projectzero.gameobject;

import main.java.projectzero.artificalbehavior.Behavior;
import main.java.projectzero.artificalbehavior.UfoBehavior;
import main.java.projectzero.handler.ComponentHandler;
import main.java.projectzero.handler.DynamicGameObjectHandler;
import main.java.projectzero.level.LevelHud;

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
        ComponentHandler.getInstance().tick(this);
        mBehavior.tick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(xPosition, yPosition, width, height);
        ComponentHandler.getInstance().render(g, this);
    }
}
