package com.kristianfischer.projectzero.command;

import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.gameobject.Player;
import com.kristianfischer.projectzero.handler.MovementHandler;

/**
 * Created by kristianhfischer on 7/13/15.
 */
public class MoveUpCommand extends Command {
    @Override
    public void execute(GameObject object) {
        if(object.getGameId().equals(GameId.Player)) {
            ((Player) object).getPlayerMovementHandler().move(MovementHandler.MovementDirection.UP);
        }
    }

    @Override
    public void stop(GameObject object) {
        if(object.getGameId().equals(GameId.Player)) {
            ((Player) object).getPlayerMovementHandler().stopMoving(MovementHandler.MovementDirection.UP);
        }
    }
}
