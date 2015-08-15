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
 * This class implements SpaceGrunt behavior:
 * - SpaceGrunt will fire projectiles that can hit the Player or Bunkers
 * - SpaceGrunt will strafe from left to right or right to left across the screen
 * - SpaceGrunt will move down once it has reached the left or right side of the screen
 * - SpaceGrunt will be part of a "hive" mentality where it is aware of other SpaceGrunt behaviors
 */
public class SpaceGruntBehavior extends Behavior implements IHiveUnderling{

    //Memory for previous SpaceGrunt movement direction
    private MovementGameComponent.MovementDirection mPreviousHorizontalDirection;
    private Command moveLeft, moveRight, moveDown;
    //Keeps track of whether SpaceGrunt has reached left or right side of screen
    private boolean mHasReachedHorizontalBoundary;
    //Handler controlling the hive behavior of this SpaceGrunt
    private HiveHandler hiveHandler;

    public SpaceGruntBehavior(GameObject gameObject) throws IllegalStateException {
        super(gameObject);
        if( !gameObject.getGameId().equals(GameId.ENEMY) )
            throw new IllegalStateException("GameObject is not an enemy");
        moveLeft = new MoveLeftCommand();
        moveRight = new MoveRightCommand();
        moveDown = new MoveDownCommand();
        moveRight.execute(this.gameObject);
        mHasReachedHorizontalBoundary = false;
        mPreviousHorizontalDirection = gameObject.getMovementComponent().getHorizontalState();
        //Register this SpaceGrunt in the Hive
        hiveHandler = HiveHandler.getInstance();
        hiveHandler.registerUnderling(this);
    }

    public void tick() {
        //Play SpaceGrunt movement animation
        gameObject.getAnimationComponent().setAnimation(GruntSpawner.GRUNT_MOVE_ANIMATION);

        //Remove SpaceGrunt from Hive if it has been destroyed
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
        boolean reachedGameOverLine = gameObject.getyPosition() + gameObject.getWidth() >=
                Game.GAME_OVER_LINE;

        if( passedRightBoundary ) {
            mHasReachedHorizontalBoundary = true;
            mPreviousHorizontalDirection = gameObject.getMovementComponent().getHorizontalState();
        } else if( passedLeftBoundary )  {
            mHasReachedHorizontalBoundary = true;
            mPreviousHorizontalDirection = gameObject.getMovementComponent().getHorizontalState();
        } else if( movingDown ) {
            moveDown.stop(gameObject);
            gameObject.setSpeed( GruntSpawner.GRUNT_SPEED );
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
