package com.kristianfischer.projectzero.handler;

import com.kristianfischer.projectzero.artificalbehavior.IHiveUnderling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristianhfischer on 7/22/15.
 */
public class HiveHandler {
    private static final HiveHandler INSTANCE = new HiveHandler();
    public static HiveHandler getInstance() { return INSTANCE; }
    private HiveHandler( ) {
        underlingList = new ArrayList<>();
        mPrepareDescent = false;
    }
    private boolean mPrepareDescent;
    private List<IHiveUnderling> underlingList;

    public void updateHiveCommands( ) {
        for( IHiveUnderling underling : underlingList ) {
            if( underling.hasReachedBoundary() ) {
                mPrepareDescent = true;
                break;
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
    }

    public void unregisterUnderling( IHiveUnderling underling) {
        underlingList.remove(underling);
    }

}
