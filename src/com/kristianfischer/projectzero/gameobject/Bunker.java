package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.handler.ComponentHandler;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;
import com.kristianfischer.projectzero.handler.GameHandler;
import com.kristianfischer.projectzero.spawner.BunkerSpawner;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
        ComponentHandler.getInstance().update(this);
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

    }

}
