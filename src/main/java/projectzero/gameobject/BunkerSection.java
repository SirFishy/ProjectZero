package main.java.projectzero.gameobject;

import main.java.projectzero.gameobject.attributes.IDamageable;
import main.java.projectzero.handler.ComponentHandler;

import java.awt.*;

/**
 * Created by fischkh1 on 7/27/15.
 */
public class BunkerSection extends GameObject implements IDamageable {

    private int mDamage;

    @Override
    public void takeDamage(int damage) {
        mDamage += damage;
    }

    @Override
    public int getDamageTaken() {
        return mDamage;
    }

    public static class Builder extends AbstractBuilder<Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        public BunkerSection build() {
            return new BunkerSection(this);
        }
    }

    public BunkerSection(Builder builder) {
        super(builder);
        ComponentHandler.getInstance().initialize(this);
    }

    @Override
    public void tick() {
        ComponentHandler.getInstance().tick(this);
        if( mDamage == 3 ) {
            isDestroyed = true;
            isActive = false;
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(xPosition, yPosition, width, height);
        ComponentHandler.getInstance().render(g, this);
    }

}
