package main.java.projectzero.handler;

import main.java.projectzero.game.Game;
import main.java.projectzero.gameobject.Ufo;
import main.java.projectzero.spawner.UfoSpawner;

import java.util.Random;

/**
 * Created by kristianhfischer on 7/31/15.
 */
public class UfoHandler {

    private Random mRandom;
    private Ufo mUfo;
    private int mSpawnTimer = 0;
    private static UfoHandler INSTANCE = new UfoHandler();
    public static UfoHandler getInstance() {
        return INSTANCE;
    }

    private UfoHandler() {
        mRandom = new Random();
        mSpawnTimer = 60 * 5;
    }

    public void update() {
        spawnUfo();
    }

    private void spawnUfo() {
        if( mUfo != null ) {
            if( mUfo.isActive() &&
                    !mUfo.isDestroyed() ) {
                return;
            } else {
                mUfo = null;
            }
        }

        if( mSpawnTimer > 0 ) {
            mSpawnTimer--;
            return;
        }

        float chance = mRandom.nextFloat();
        if( chance <= (1.0f / (60.0f * 10))) {
            mSpawnTimer = 60 * 8;
            int spawnSide = mRandom.nextInt(2);
            if( spawnSide == 0 ) {
                //Spawn Left
                mUfo = UfoSpawner.SpawnUfo(-1 * UfoSpawner.UFO_WIDTH,
                        10, 200);
                DynamicGameObjectHandler.getInstance().addNewGameObject(mUfo);
                System.out.println("Spawned Ufo Left");
            } else {
                mUfo = UfoSpawner.SpawnUfo(Game.WIDTH + 1,
                        10, 200);
                DynamicGameObjectHandler.getInstance().addNewGameObject(mUfo);
                System.out.println("Spawned Ufo Right");
            }
        }
    }

}
