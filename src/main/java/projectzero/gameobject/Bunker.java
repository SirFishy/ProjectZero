package main.java.projectzero.gameobject;

import main.java.projectzero.component.GameComponent;
import main.java.projectzero.handler.ComponentHandler;
import main.java.projectzero.handler.DynamicGameObjectHandler;
import main.java.projectzero.handler.GameHandler;

import java.awt.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Created by fischkh1 on 7/27/15.
 */
public class Bunker extends GameObject {

    private ConcurrentLinkedQueue<BunkerSection> mBunkerSections;

    public static class Builder extends AbstractBuilder<Builder> {

        private ConcurrentLinkedQueue<BunkerSection> mBunkerSections = new ConcurrentLinkedQueue<>();

        @Override
        protected Builder self() {
            return this;
        }

        public Builder bunkerSection( BunkerSection section ) {
            mBunkerSections.add( section );
            return this;
        }

        public Bunker build() {
            return new Bunker(this);
        }
    }

    public Bunker(Builder builder) {
        super(builder);
        this.mBunkerSections = builder.mBunkerSections;
        ComponentHandler.getInstance().initialize(this);
        for( BunkerSection section : mBunkerSections ) {
            GameHandler.getInstance().addGameObject(section);
        }
    }

    @Override
    public void tick() {
        ComponentHandler.getInstance().tick(this);
        Iterator<BunkerSection> bunkers;
        for( bunkers = mBunkerSections.iterator(); bunkers.hasNext(); ) {
            BunkerSection section = bunkers.next();
            if( section.isDestroyed() ) {
                bunkers.remove();
                DynamicGameObjectHandler.getInstance().addDestroyedGameObject(section);
            }
        }

        if( mBunkerSections.isEmpty() ) {
            isDestroyed = true;
            isActive = false;
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        ComponentHandler.getInstance().render(g, this);
    }

}
