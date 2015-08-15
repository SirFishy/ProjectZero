package main.java.projectzero.command;

import main.java.projectzero.gameobject.GameObject;

/**
 * Created by kristianhfischer on 7/10/15.
 *
 * Command helps map behavior to player input. This contract will be extended by all
 * actions that a player can perform, and can even be used for NPC behavior too.
 */
public abstract class Command {

    public abstract void execute(GameObject object);
    public abstract void stop(GameObject object);
}
