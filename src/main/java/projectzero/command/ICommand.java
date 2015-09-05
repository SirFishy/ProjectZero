package main.java.projectzero.command;

import main.java.projectzero.gameobject.GameObject;

/**
 * Created by kristianhfischer on 7/10/15.
 *
 * ICommand helps map behavior to player input. This contract will be extended by all
 * actions that a player can perform, and can even be used for NPC behavior too.
 */
public interface ICommand {
    void execute(GameObject object);
    void stop(GameObject object);
}
