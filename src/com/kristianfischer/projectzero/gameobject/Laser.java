package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.artificalbehavior.Behavior;
import com.kristianfischer.projectzero.artificalbehavior.EnemyProjectileBehavior;
import com.kristianfischer.projectzero.artificalbehavior.PlayerProjectileBehavior;
import com.kristianfischer.projectzero.game.Game;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.handler.ComponentHandler;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/14/15.
 */
public class Laser extends GameObject {

    private Behavior behavior;

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
        if( gameId.equals(GameId.PLAYER_PROJECTILE) )
            behavior = new PlayerProjectileBehavior(this);
        else if( gameId.equals(GameId.ENEMY_PROJECTILE) )
            behavior = new EnemyProjectileBehavior(this);
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
