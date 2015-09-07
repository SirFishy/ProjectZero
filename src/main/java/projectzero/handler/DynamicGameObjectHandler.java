package main.java.projectzero.handler;

import main.java.projectzero.gameobject.GameObject;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by fischkh1 on 7/16/15.
 */
public class DynamicGameObjectHandler {

    private static final DynamicGameObjectHandler INSTANCE = new DynamicGameObjectHandler();
    private ConcurrentLinkedQueue<GameObject> mNewGameObjects;
    private ConcurrentLinkedQueue<GameObject> mDestroyedGameObjects;
    private DynamicGameObjectHandler() {
        mNewGameObjects = new ConcurrentLinkedQueue<>();
        mDestroyedGameObjects = new ConcurrentLinkedQueue<>();
    }
    public static DynamicGameObjectHandler getInstance() { return INSTANCE; }

    public void addNewGameObject(GameObject object) { mNewGameObjects.add(object); }
    public boolean hasNextNewGameObject() {
        if ( mNewGameObjects.isEmpty() ) return false;
        else return true;
    }
    public GameObject getNextNewGameObject() throws IllegalStateException {
        if( mNewGameObjects.isEmpty() ) throw new IllegalStateException("No GameObjects available. ");
        return mNewGameObjects.poll();
    }

    public void addDestroyedGameObject(GameObject object) { mDestroyedGameObjects.add(object); }
    public boolean hasNextDestroyedGameObject() {
        if ( mDestroyedGameObjects.isEmpty() ) return false;
        else return true;
    }
    public GameObject getNextDestroyedGameObject() throws IllegalStateException {
        if( mDestroyedGameObjects.isEmpty() ) throw new IllegalStateException("No GameObjects available. ");
        return mDestroyedGameObjects.poll();
    }

    public void clear() {
        mNewGameObjects.clear();
        mDestroyedGameObjects.clear();
    }
}
