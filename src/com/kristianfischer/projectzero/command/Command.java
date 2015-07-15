package com.kristianfischer.projectzero.command;

import com.kristianfischer.projectzero.gameobject.GameObject;

/**
 * Created by kristianhfischer on 7/10/15.
 */
public abstract class Command {
    public Command() {}

    public abstract void execute(GameObject object);
    public abstract void stop(GameObject object);
}
