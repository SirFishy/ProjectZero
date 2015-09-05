package main.java.projectzero.command;

import main.java.projectzero.game.GameId;
import main.java.projectzero.gameobject.GameObject;
import main.java.projectzero.component.MovementGameComponent;

/**
 * Created by kristianhfischer on 7/13/15.
 *
 * This command will move GameObjects in the (-) x direction
 */
public class MoveLeftCommand implements ICommand {
    @Override
    public void execute(GameObject object) {
        if(object.getGameId().equals(GameId.PLAYER)) {
            object.getMovementComponent().move(MovementGameComponent.MovementDirection.LEFT);
            return;
        }

        if( object.getGameId().equals(GameId.ENEMY)) {
            object.getMovementComponent().move(MovementGameComponent.MovementDirection.LEFT);
            return;
        }

        if( object.getGameId().equals(GameId.UFO)) {
            object.getMovementComponent().move(MovementGameComponent.MovementDirection.LEFT);
            return;
        }
    }

    @Override
    public void stop(GameObject object) {
        if(object.getGameId().equals(GameId.PLAYER)) {
           object.getMovementComponent().stopMoving(MovementGameComponent.MovementDirection.LEFT);
            return;
        }

        if( object.getGameId().equals(GameId.ENEMY)) {
            object.getMovementComponent().stopMoving(MovementGameComponent.MovementDirection.LEFT);
            return;
        }

        if( object.getGameId().equals(GameId.UFO)) {
            object.getMovementComponent().stopMoving(MovementGameComponent.MovementDirection.LEFT);
            return;
        }
    }
}
