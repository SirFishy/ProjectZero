package com.kristianfischer.projectzero.command;

import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.gameobject.Player;

/**
 * Created by fischkh1 on 7/16/15.
 */
public class FireCommand extends Command {

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
