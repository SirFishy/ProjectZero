package com.kristianfischer.projectzero.handler;

import com.kristianfischer.projectzero.gameobject.GameObject;

import java.util.LinkedList;

/**
 * Created by fischkh1 on 7/16/15.
 */
public class DynamicObjectHandler {

    private static final DynamicObjectHandler INSTANCE = new DynamicObjectHandler();
    private LinkedList<GameObject> mNewGameObjects;
    private DynamicObjectHandler() { mNewGameObjects = new LinkedList<>(); }
    public static DynamicObjectHandler getInstance() { return INSTANCE; }

    public void addNewGameObject(GameObject object) { mNewGameObjects.push(object); }
    public boolean hasNextGameObject() {
        if ( mNewGameObjects.isEmpty() ) return false;
        else return true;
    }
    public GameObject getNextGameObject() throws IllegalStateException {
        if( mNewGameObjects.isEmpty() ) throw new IllegalStateException("No GameObjects available. ");
        return mNewGameObjects.removeLast();
    }
}
