package main.java.projectzero.artificalbehavior;

import main.java.projectzero.command.ICommand;
import main.java.projectzero.command.MoveLeftCommand;
import main.java.projectzero.command.MoveRightCommand;
import main.java.projectzero.component.MovementGameComponent;
import main.java.projectzero.game.Game;
import main.java.projectzero.gameobject.GameObject;
import main.java.projectzero.spawner.UfoSpawner;

import java.util.Random;

/**
 * Created by kristianhfischer on 7/31/15.
 *
 * This class implements UFO behavior:
 * - UFO will randomly spawn left or right
 * - UFO will move in "Left" and "Right" directions a random number of times
 * - UFO will suddenly change directions a random number of times
 * - UFO will exit the "Left" or "Right" side of the screen after a certain number of direction changes
 */
public class UfoBehavior extends Behavior {

    private ICommand mMoveLeft, mMoveRight;
    private Random mRandom;
    //Number of turns a UFO will perform before it attempts to escape
    private int mNumberOfTurnsRemaining;
    //Keeps track of whether UFO has intially reached the center of the screen
    private boolean mHasReachedCenter;

    public UfoBehavior(GameObject gameObject) {
        super(gameObject);
        mMoveLeft = new MoveLeftCommand();
        mMoveRight = new MoveRightCommand();
        mRandom = new Random();

        /*
         * If the UFO was spawned right, then move in the left direction to appear on the screen.
         * else
         * Move in the right direction to appear on the screen
         */
        if( this.gameObject.getxPosition() > Game.WIDTH ) {
            mMoveLeft.execute(this.gameObject);
            System.out.println("Ufo Moving left");
        } else {
            mMoveRight.execute(this.gameObject);
            System.out.println("Ufo Moving right");
        }
        mHasReachedCenter = false;

        //Randomly determine how many times the UFO will change direction
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

                //Switch directions if reached left or right side of screen, or a 1/10 chance per second
                if( chance <= (1.0f / (60.0f * 10.0f))
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

                //UFO was not destroyed, so we only set it to not active. This will prevent the player
                //from getting points for the UFO since the player was unable to destroy it
                if( exitedLeft || exitedRight ) {
                    gameObject.setIsActive(false);
                }
            }
        }
    }

    /**
     * gotToTheCenter is a helper method that will prevent the UFO from changing directions until
     * the UFO is able to reach the center of the Game Window when the UFO first spawns. This gives the player
     * a chance to react to the UFO when it appears on the screen. Once the UFO reaches the center of the screen
     * for the first time, then this method will always return true.
     * @return true if UFO reached center, false if it did not
     */
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

    /**
     * switchDirections is a helper method that will switch the direction the UFO is traveling.
     * If it was moving Left, then switch movement direction to right.
     * If it was moving Right, then switch movement direction to left.
     */

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

    /**
     * isMovingLeft determines if UFO is moving left
     * @return true if UFO is moving left, false if it is not
     */

    private boolean isMovingLeft() {
        return gameObject.getMovementComponent().getHorizontalState()
                .equals(MovementGameComponent.MovementDirection.LEFT);
    }

    /**
     * isMovingRight determines if UFO is moving right
     * @return true if UFO is moving right, false if it is not
     */
    private boolean isMovingRight() {
        return gameObject.getMovementComponent().getHorizontalState()
                .equals(MovementGameComponent.MovementDirection.RIGHT);
    }
}
