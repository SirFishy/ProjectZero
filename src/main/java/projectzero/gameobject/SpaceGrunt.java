package main.java.projectzero.gameobject;

import main.java.projectzero.artificalbehavior.Behavior;
import main.java.projectzero.artificalbehavior.SpaceGruntBehavior;
import main.java.projectzero.game.Game;
import main.java.projectzero.handler.ComponentHandler;
import main.java.projectzero.handler.DynamicGameObjectHandler;
import main.java.projectzero.level.LevelHud;
import main.java.projectzero.spawner.GruntSpawner;

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
        mScore = builder.mScore;
        ComponentHandler.getInstance().initialize(this);
        behavior = new SpaceGruntBehavior(this);
    }

    @Override
    public void tick() {
        if( isDestroyed ) {
            //Only score if enemy hasn't reached game over line
            if( yPosition < Game.GAME_OVER_LINE ) {
                LevelHud.getInstance().addScore(mScore);
            }
            //Tick the behavior here to allow it to unregister from the HiveHandler
            behavior.tick();
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(this);
            return;
        }
        ComponentHandler.getInstance().tick(this);
        behavior.tick();
    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.white);
        //g.fillRect(xPosition, yPosition, width, height);
        ComponentHandler.getInstance().render(g, this);
    }

    public Behavior getBehavior() {
        return behavior;
    }
}
