package main.java.projectzero.gameobject;

import main.java.projectzero.artificalbehavior.*;
import main.java.projectzero.game.GameId;
import main.java.projectzero.gameobject.attributes.IDamageable;
import main.java.projectzero.gameobject.attributes.IWeapon;
import main.java.projectzero.handler.ComponentHandler;
import main.java.projectzero.handler.DynamicGameObjectHandler;

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
        if( isDestroyed ) {
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(this);
            return;
        }
        ComponentHandler.getInstance().tick(this);
        behavior.tick();

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPosition, yPosition, width, height);
        ComponentHandler.getInstance().render(g, this);
    }

    public int getDamage() {
        return mDamage;
    }

    public void setDamage(int mDamage) {
        this.mDamage = mDamage;
    }
}
