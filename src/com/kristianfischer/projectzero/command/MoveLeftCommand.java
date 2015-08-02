package com.kristianfischer.projectzero.command;

import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.component.MovementGameComponent;

/**
 * Created by kristianhfischer on 7/13/15.
 */
public class MoveLeftCommand extends Command {
    @Override
    public void execute(GameObject object) {
        if(object.getGameId().equals(GameId.PLAYER)) {
            object.getMovementComponent().move(MovementGameComponent.MovementDirection.LEFT);
        }

        if( object.getGameId().equals(GameId.ENEMY)) {
            object.getMovementComponent().move(MovementGameComponent.MovementDirection.LEFT);
        }

        if( object.getGameId().equals(GameId.UFO)) {
            object.getMovementComponent().move(MovementGameComponent.MovementDirection.LEFT);
        }
    }

    @Override
    public void stop(GameObject object) {
        if(object.getGameId().equals(GameId.PLAYER)) {
           object.getMovementComponent().stopMoving(MovementGameComponent.MovementDirection.LEFT);
        }

        if( object.getGameId().equals(GameId.ENEMY)) {
            object.getMovementComponent().stopMoving(MovementGameComponent.MovementDirection.LEFT);
        }

        if( object.getGameId().equals(GameId.UFO)) {
            object.getMovementComponent().stopMoving(MovementGameComponent.MovementDirection.LEFT);
        }
    }
}
