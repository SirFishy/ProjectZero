package main.java.projectzero.handler;

import main.java.projectzero.artificalbehavior.IHiveUnderling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kristianhfischer on 7/22/15.
 */
public class HiveHandler {
    private static final HiveHandler INSTANCE = new HiveHandler();
    public static HiveHandler getInstance() { return INSTANCE; }
    private HiveHandler( ) {
        underlingList = new ArrayList<>();
        mPrepareDescent = false;
        mMaxProjectiles = 8;
        mNumberOfEnemies = 0;
        mRandom = new Random();
    }
    private boolean mPrepareDescent;
    private int mMaxProjectiles;
    private List<IHiveUnderling> underlingList;
    private Random mRandom;
    private int mNumberOfEnemies;

    public void updateHiveCommands( ) {
        int currentProjectiles = GameHandler.getInstance().getNumberOfEnemyProjectiles();
        for( IHiveUnderling underling : underlingList ) {

            if( underling.hasReachedBoundary() && !mPrepareDescent ) {
                mPrepareDescent = true;
            }

            if( currentProjectiles < mMaxProjectiles ) {
                float chanceToFire = mRandom.nextFloat();
                //60 updates per second
                //Want to average a 33% chance to shoot every second
                //Therefore, chance is 1/(60 frames * 3 seconds)
                if( chanceToFire <= (1.0f/(60.0f * (3.0f + mNumberOfEnemies)) ) ) {
                    underling.fireProjectile();
                    currentProjectiles++;
                }
            }
        }

        if( mPrepareDescent ) {
            for( IHiveUnderling underling : underlingList) {
                underling.prepareDescent();
            }
            mPrepareDescent = false;
        }
    }

    public void registerUnderling( IHiveUnderling underling ) {
        underlingList.add(underling);
        mNumberOfEnemies++;
    }

    public void unregisterUnderling( IHiveUnderling underling) {
        underlingList.remove(underling);
        mNumberOfEnemies--;
    }

    public int getNumberOfEnemies() {
        return mNumberOfEnemies;
    }

    public void clear() {
        underlingList.clear();
        mNumberOfEnemies = 0;
        mPrepareDescent = false;
        mRandom = new Random();
    }

}
