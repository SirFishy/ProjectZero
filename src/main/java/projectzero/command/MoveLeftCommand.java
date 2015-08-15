package main.java.projectzero.command;

import main.java.projectzero.game.GameId;
import main.java.projectzero.gameobject.GameObject;
import main.java.projectzero.component.MovementGameComponent;

/**
 * Created by kristianhfischer on 7/13/15.
 *
 * This command will move GameObjects in the (-) x direction
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
