package com.kristianfischer.projectzero.command;

import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.gameobject.Player;
import com.kristianfischer.projectzero.component.MovementComponent;

/**
 * Created by kristianhfischer on 7/13/15.
 */
public class MoveDownCommand extends Command {
    @Override
    public void execute(GameObject object) {
        if(object.getGameId().equals(GameId.PLAYER)) {
            object.getMovementComponent().move(MovementComponent.MovementDirection.DOWN);
        }
    }

    @Override
    public void stop(GameObject object) {
        if(object.getGameId().equals(GameId.PLAYER)) {
            object.getMovementComponent().stopMoving(MovementComponent.MovementDirection.DOWN);
        }
    }
}
