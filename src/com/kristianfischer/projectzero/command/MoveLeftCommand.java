package com.kristianfischer.projectzero.command;

import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.gameobject.Player;
import com.kristianfischer.projectzero.state.PlayerMovementHandler;

/**
 * Created by kristianhfischer on 7/13/15.
 */
public class MoveLeftCommand extends Command {
    @Override
    public void execute(GameObject object) {
        if(object.getGameId().equals(GameId.Player)) {
            ((Player) object).getPlayerMovementHandler().movePlayer(PlayerMovementHandler.MovementDirection.LEFT);
        }
    }

    @Override
    public void stop(GameObject object) {
        if(object.getGameId().equals(GameId.Player)) {
            ((Player) object).getPlayerMovementHandler().stopPlayer(PlayerMovementHandler.MovementDirection.LEFT);
        }
    }
}
