package com.kristianfischer.projectzero.command;

import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.component.MovementGameComponent;

/**
 * Created by kristianhfischer on 7/13/15.
 */
public class MoveUpCommand extends Command {
    @Override
    public void execute(GameObject object) {
        if(object.getGameId().equals(GameId.PLAYER)) {
            object.getMovementComponent().move(MovementGameComponent.MovementDirection.UP);
        }

        if( object.getGameId().equals(GameId.ENEMY)) {
            object.getMovementComponent().move(MovementGameComponent.MovementDirection.UP);
        }

        if( object.getGameId().equals(GameId.PLAYER_PROJECTILE)) {
            object.getMovementComponent().move(MovementGameComponent.MovementDirection.UP);
        }
    }

    @Override
    public void stop(GameObject object) {
        if(object.getGameId().equals(GameId.PLAYER)) {
            object.getMovementComponent().stopMoving(MovementGameComponent.MovementDirection.UP);
        }

        if( object.getGameId().equals(GameId.ENEMY)) {
            object.getMovementComponent().stopMoving(MovementGameComponent.MovementDirection.UP);
        }

        if( object.getGameId().equals(GameId.PLAYER_PROJECTILE)) {
            object.getMovementComponent().stopMoving(MovementGameComponent.MovementDirection.UP);
        }
    }
}
