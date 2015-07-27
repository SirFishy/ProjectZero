package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.artificalbehavior.*;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.attributes.IDamageable;
import com.kristianfischer.projectzero.gameobject.attributes.IWeapon;
import com.kristianfischer.projectzero.handler.ComponentHandler;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/14/15.
 */
public class Laser extends GameObject implements IWeapon {

    private Behavior behavior;
    private int mDamage;

    @Override
    public void applyDamage(IDamageable damageable) {
        damageable.takeDamage(mDamage);
    }

    public static class Builder extends AbstractBuilder<Builder> {
        private int mDamage = 0;

        @Override
        protected Builder self() {
            return this;
        }

        public Builder damage(int damage) {
            mDamage = damage;
            return this;
        }

        public Laser build() {
            return new Laser(this);
        }
    }

    public Laser(Builder builder) {
        super(builder);
        this.mDamage = builder.mDamage;
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
        collisionComponent.getHitbox().render(g);
    }

    public int getDamage() {
        return mDamage;
    }

    public void setDamage(int mDamage) {
        this.mDamage = mDamage;
    }
}
