package main.java.projectzero.command;

import main.java.projectzero.game.GameId;
import main.java.projectzero.gameobject.GameObject;
import main.java.projectzero.gameobject.Player;

/**
 * Created by fischkh1 on 7/16/15.
 *
 * This command will fire the GameObject's equipped weapon
 */
public class FireCommand implements ICommand {

    public FireCommand() {
    }

    @Override
    public void execute(GameObject object) {
        if( object.getGameId().equals(GameId.PLAYER) ) {
            ((Player) object).fire();
        }
    }

    @Override
    public void stop(GameObject object) {
        if( object.getGameId().equals(GameId.PLAYER) ) {
            ((Player) object).ceaseFire();
        }
    }
}
