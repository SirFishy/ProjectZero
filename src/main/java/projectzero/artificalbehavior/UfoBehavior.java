package main.java.projectzero.artificalbehavior;

import main.java.projectzero.command.Command;
import main.java.projectzero.command.MoveLeftCommand;
import main.java.projectzero.command.MoveRightCommand;
import main.java.projectzero.component.MovementGameComponent;
import main.java.projectzero.game.Game;
import main.java.projectzero.gameobject.GameObject;
import main.java.projectzero.spawner.UfoSpawner;

import java.util.Random;

/**
 * Created by kristianhfischer on 7/31/15.
 */
public class UfoBehavior extends Behavior {

    private Command mMoveLeft, mMoveRight;
    private Random mRandom;
    private int mNumberOfTurnsRemaining;
    private boolean mHasReachedCenter;

    public UfoBehavior(GameObject gameObject) {
        super(gameObject);
        mMoveLeft = new MoveLeftCommand();
        mMoveRight = new MoveRightCommand();
        mRandom = new Random();
        if( this.gameObject.getxPosition() > Game.WIDTH ) {
            mMoveLeft.execute(this.gameObject);
            System.out.println("Ufo Moving left");
        } else {
            mMoveRight.execute(this.gameObject);
            System.out.println("Ufo Moving right");
        }
        mHasReachedCenter = false;
        mNumberOfTurnsRemaining = mRandom.nextInt(UfoSpawner.MAX_NUMBER_OF_TURNS -
                UfoSpawner.MIN_NUMBER_OF_TURNS) + UfoSpawner.MIN_NUMBER_OF_TURNS;
        System.out.println("Number of turns remaining: " + mNumberOfTurnsRemaining);
    }

    @Override
    public void tick() {
        if( gotToTheCenter() ) {
            if( mNumberOfTurnsRemaining > 0 ) {

                boolean reachedRightBorder = gameObject.getxVelocity() > 0 &&
                        gameObject.getxPosition() >= Game.WIDTH - gameObject.getWidth();
                boolean reachedLeftBorder = gameObject.getxVelocity() < 0 &&
                        gameObject.getxPosition() <= 0;

                float chance = mRandom.nextFloat();
                if( chance <= (1.0f / (60.0f * 10))
                        || reachedRightBorder ||
                        reachedLeftBorder) {
                    switchDirections();
                    mNumberOfTurnsRemaining--;
                }
            } else if( mNumberOfTurnsRemaining == 0) {
                boolean exitedLeft = gameObject.getxPosition() <=
                        0 - gameObject.getWidth();
                boolean exitedRight = gameObject.getxPosition() >=
                        Game.WIDTH;
                if( exitedLeft || exitedRight ) {
                    gameObject.setIsActive(false);
                }
            }
        }
    }

    private boolean gotToTheCenter() {
        if( mHasReachedCenter )
            return true;

        if( isMovingLeft() && gameObject.getxPosition() < Game.WIDTH / 2) {
            mHasReachedCenter = true;
            return true;
        }

        if( isMovingRight() && gameObject.getxPosition() > Game.WIDTH / 2) {
            mHasReachedCenter = true;
            return true;
        }
        return false;
    }

    private void switchDirections() {
        if( isMovingLeft() ) {
            mMoveLeft.stop(gameObject);
            mMoveRight.execute(gameObject);
            System.out.println("Switching to right");
        } else {
            mMoveRight.stop(gameObject);
            mMoveLeft.execute(gameObject);
            System.out.println("Switching to left");
        }
    }

    private boolean isMovingLeft() {
        return gameObject.getMovementComponent().getHorizontalState()
                .equals(MovementGameComponent.MovementDirection.LEFT);
    }

    private boolean isMovingRight() {
        return gameObject.getMovementComponent().getHorizontalState()
                .equals(MovementGameComponent.MovementDirection.RIGHT);
    }
}
