package com.kristianfischer.projectzero.artificalbehavior;

import com.kristianfischer.projectzero.command.*;
import com.kristianfischer.projectzero.component.MovementGameComponent;
import com.kristianfischer.projectzero.game.Game;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;
import com.kristianfischer.projectzero.handler.HiveHandler;

/**
 * Created by kristianhfischer on 7/20/15.
 */
public class SpaceGruntBehavior extends Behavior implements IHiveUnderling{

    private MovementGameComponent.MovementDirection mPreviousHorizontalDirection;
    private Command moveLeft, moveRight, moveDown;
    private int gruntSpeed;
    private boolean mHasReachedBoundary;
    private HiveHandler hiveHandler;

    public SpaceGruntBehavior(GameObject gameObject) throws IllegalStateException {
        super(gameObject);
        if( !gameObject.getGameId().equals(GameId.ENEMY) )
            throw new IllegalStateException("GameObject is not an enemy");
        gruntSpeed = this.gameObject.getSpeed();
        moveLeft = new MoveLeftCommand();
        moveRight = new MoveRightCommand();
        moveDown = new MoveDownCommand();
        moveRight.execute(this.gameObject);
        mHasReachedBoundary = false;
        mPreviousHorizontalDirection = gameObject.getMovementComponent().getHorizontalState();
        hiveHandler = HiveHandler.getInstance();
        hiveHandler.registerUnderling(this);
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
            mHasReachedBoundary = true;
            mPreviousHorizontalDirection = gameObject.getMovementComponent().getHorizontalState();
        } else if( passedLeftBoundary )  {
            mHasReachedBoundary = true;
            mPreviousHorizontalDirection = gameObject.getMovementComponent().getHorizontalState();
        } else if( movingDown ) {
            moveDown.stop(gameObject);
            gameObject.setSpeed( gruntSpeed );
            if( mPreviousHorizontalDirection.equals(MovementGameComponent.MovementDirection.LEFT) ) {
                moveRight.execute(gameObject);
            } else {
                moveLeft.execute(gameObject);
            }
        }

        if( gameObject.isDestroyed() ) {
            hiveHandler.unregisterUnderling(this);
        }

        if( reachedGameOverLine ) {
            hiveHandler.unregisterUnderling(this);
            gameObject.setIsActive(false);
            gameObject.setIsDestroyed(true);
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(gameObject);
        }
    }

    @Override
    public void prepareDescent( ) {
        gameObject.setSpeed( gameObject.getHeight() + 10 );
        if( mPreviousHorizontalDirection.equals(MovementGameComponent.MovementDirection.LEFT) ) {
            moveLeft.stop(gameObject);
        } else {
            moveRight.stop(gameObject);
        }
        moveDown.execute(gameObject);
        mHasReachedBoundary = false;
    }

    @Override
    public boolean hasReachedBoundary() {
        return mHasReachedBoundary;
    }
}
