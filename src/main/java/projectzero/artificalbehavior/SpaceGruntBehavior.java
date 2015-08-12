package main.java.projectzero.artificalbehavior;

import main.java.projectzero.command.*;
import main.java.projectzero.component.MovementGameComponent;
import main.java.projectzero.game.Game;
import main.java.projectzero.game.GameId;
import main.java.projectzero.gameobject.GameObject;
import main.java.projectzero.gameobject.Laser;
import main.java.projectzero.handler.DynamicGameObjectHandler;
import main.java.projectzero.handler.HiveHandler;
import main.java.projectzero.spawner.GruntSpawner;
import main.java.projectzero.spawner.LaserSpawner;

/**
 * Created by kristianhfischer on 7/20/15.
 */
public class SpaceGruntBehavior extends Behavior implements IHiveUnderling{

    private MovementGameComponent.MovementDirection mPreviousHorizontalDirection;
    private Command moveLeft, moveRight, moveDown;
    private int gruntSpeed;
    private boolean mHasReachedHorizontalBoundary;
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
        mHasReachedHorizontalBoundary = false;
        mPreviousHorizontalDirection = gameObject.getMovementComponent().getHorizontalState();
        hiveHandler = HiveHandler.getInstance();
        hiveHandler.registerUnderling(this);
    }

    public void tick() {
        gameObject.getAnimationComponent().setAnimation(GruntSpawner.GRUNT_MOVE_ANIMATION);
        if( gameObject.isDestroyed() ) {
            gameObject.getAnimationComponent().stopAnimating();
            hiveHandler.unregisterUnderling(this);
            return;
        }

        boolean passedRightBoundary = gameObject.getxVelocity() > 0 &&
                gameObject.getxPosition() > (Game.WIDTH - (10 + gameObject.getWidth()));
        boolean passedLeftBoundary = gameObject.getxVelocity() < 0 &&
                gameObject.getxPosition() < (10 + gameObject.getWidth());
        boolean movingDown = gameObject.getMovementComponent().getVerticalState()
                .equals(MovementGameComponent.MovementDirection.DOWN);
        boolean reachedGameOverLine = gameObject.getyPosition() + gameObject.getWidth() >= Game.HEIGHT - 32;

        if( passedRightBoundary ) {
            mHasReachedHorizontalBoundary = true;
            mPreviousHorizontalDirection = gameObject.getMovementComponent().getHorizontalState();
        } else if( passedLeftBoundary )  {
            mHasReachedHorizontalBoundary = true;
            mPreviousHorizontalDirection = gameObject.getMovementComponent().getHorizontalState();
        } else if( movingDown ) {
            moveDown.stop(gameObject);
            gameObject.setSpeed( gruntSpeed );
            if( mPreviousHorizontalDirection.equals(MovementGameComponent.MovementDirection.LEFT) ) {
                moveRight.execute(gameObject);
                mPreviousHorizontalDirection = MovementGameComponent.MovementDirection.RIGHT;
            } else {
                moveLeft.execute(gameObject);
                mPreviousHorizontalDirection = MovementGameComponent.MovementDirection.LEFT;
            }
        }

        if( reachedGameOverLine ) {
            hiveHandler.unregisterUnderling(this);
            gameObject.setIsDestroyed(true);
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
        mHasReachedHorizontalBoundary = false;
    }

    @Override
    public void fireProjectile() {

        Laser laser = LaserSpawner.SpawnLaser(gameObject.getxPosition() + gameObject.getWidth() / 2,
                gameObject.getyPosition() + gameObject.getHeight(), GameId.ENEMY_PROJECTILE);
        DynamicGameObjectHandler.getInstance().addNewGameObject(laser);
    }

    @Override
    public boolean hasReachedBoundary() {
        return mHasReachedHorizontalBoundary;
    }
}
