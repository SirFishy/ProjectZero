package com.kristianfischer.projectzero.artificalbehavior;

import com.kristianfischer.projectzero.command.*;
import com.kristianfischer.projectzero.component.MovementGameComponent;
import com.kristianfischer.projectzero.game.Game;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;

/**
 * Created by kristianhfischer on 7/20/15.
 */
public class SpaceGruntBehavior extends Behavior{

    private MovementGameComponent.MovementDirection mPreviousHorizontalDirection;
    private Command moveLeft, moveRight, moveDown;
    private int gruntSpeed;

    public SpaceGruntBehavior(GameObject gameObject) throws IllegalStateException {
        super(gameObject);
        if( !gameObject.getGameId().equals(GameId.ENEMY) )
            throw new IllegalStateException("GameObject is not an enemy");
        gruntSpeed = this.gameObject.getSpeed();
        moveLeft = new MoveLeftCommand();
        moveRight = new MoveRightCommand();
        moveDown = new MoveDownCommand();
        moveRight.execute(this.gameObject);
        mPreviousHorizontalDirection = gameObject.getMovementComponent().getHorizontalState();

    }

    public void update() {

        boolean passedRightBoundary = gameObject.getxVelocity() > 0 &&
                gameObject.getxPosition() > (Game.WIDTH - (10 + gameObject.getWidth()));
        boolean passedLeftBoundary = gameObject.getxVelocity() < 0 &&
                gameObject.getxPosition() < (0 + (10 + gameObject.getWidth()));
        boolean movingDown = gameObject.getMovementComponent().getVerticalState()
                .equals(MovementGameComponent.MovementDirection.DOWN);
        boolean reachedGameOverLine = gameObject.getyPosition() + gameObject.getWidth() >= Game.HEIGHT - 32;

        if( passedRightBoundary ) {
            mPreviousHorizontalDirection = gameObject.getMovementComponent().getHorizontalState();
            moveRight.stop(gameObject);
            gameObject.setSpeed(gameObject.getHeight() + 10);
            moveDown.execute(gameObject);
        } else if( passedLeftBoundary )  {
            mPreviousHorizontalDirection = gameObject.getMovementComponent().getHorizontalState();
            moveLeft.stop(gameObject);
            gameObject.setSpeed(gameObject.getHeight() + 10);
            moveDown.execute(gameObject);
        } else if( movingDown ) {
            moveDown.stop(gameObject);
            gameObject.setSpeed( gruntSpeed );
            if( mPreviousHorizontalDirection.equals(MovementGameComponent.MovementDirection.LEFT) ) {
                moveRight.execute(gameObject);
            } else {
                moveLeft.execute(gameObject);
            }
        }

        if( reachedGameOverLine ) {
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(gameObject);
        }
    }
}
