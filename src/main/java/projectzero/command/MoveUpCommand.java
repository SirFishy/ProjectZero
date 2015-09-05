package main.java.projectzero.command;

import main.java.projectzero.game.GameId;
import main.java.projectzero.gameobject.GameObject;
import main.java.projectzero.component.MovementGameComponent;

/**
 * Created by kristianhfischer on 7/13/15.
 *
 * This command will move GameObjects in the (+) y direction
 */
public class MoveUpCommand implements ICommand {
    @Override
    public void execute(GameObject object) {
        if(object.getGameId().equals(GameId.PLAYER)) {
            object.getMovementComponent().move(MovementGameComponent.MovementDirection.UP);
            return;
        }

        if( object.getGameId().equals(GameId.ENEMY)) {
            object.getMovementComponent().move(MovementGameComponent.MovementDirection.UP);
            return;
        }

        if( object.getGameId().equals(GameId.PLAYER_PROJECTILE)) {
            object.getMovementComponent().move(MovementGameComponent.MovementDirection.UP);
            return;
        }
    }

    @Override
    public void stop(GameObject object) {
        if(object.getGameId().equals(GameId.PLAYER)) {
            object.getMovementComponent().stopMoving(MovementGameComponent.MovementDirection.UP);
            return;
        }

        if( object.getGameId().equals(GameId.ENEMY)) {
            object.getMovementComponent().stopMoving(MovementGameComponent.MovementDirection.UP);
            return;
        }

        if( object.getGameId().equals(GameId.PLAYER_PROJECTILE)) {
            object.getMovementComponent().stopMoving(MovementGameComponent.MovementDirection.UP);
            return;
        }
    }
}
