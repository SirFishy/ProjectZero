package com.kristianfischer.projectzero.command;

import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.handler.GameHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kristianhfischer on 7/10/15.
 */
public abstract class Command {
    protected List<GameObject> objectList;
    public Command() {
        objectList = new LinkedList<GameObject>();
    }

    public List<GameObject> getSpawnedObjects() {
        return objectList;
    }

    public abstract void execute(GameObject object);
    public abstract void stop(GameObject object);
}
