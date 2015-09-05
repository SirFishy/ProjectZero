package main.java.projectzero.component;

import main.java.projectzero.game.Game;
import main.java.projectzero.gameobject.GameObject;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by kristianhfischer on 7/13/15.
 * A IGameComponent that can be added to a GameObject to call all of the required methods for movement
 */
public class MovementGameComponent implements IGameComponent {

    private MovementDirection verticalState;
    private MovementDirection horizontalState;
    private AtomicBoolean mIsMovingLeft;
    private AtomicBoolean mIsMovingRight;
    private AtomicBoolean mIsMovingUp;
    private AtomicBoolean mIsMovingDown;
    private GameObject mGameObject;
    private boolean mClamp;

    public enum MovementDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE
    }

    public MovementGameComponent() {
    }

    @Override
    public void initialize( GameObject gameObject ) {
        mGameObject = gameObject;
        mGameObject.setxVelocity(0);
        mGameObject.setyVelocity(0);
        verticalState = MovementDirection.NONE;
        horizontalState = MovementDirection.NONE;
        mIsMovingLeft = new AtomicBoolean(false);
        mIsMovingRight = new AtomicBoolean(false);
        mIsMovingUp = new AtomicBoolean(false);
        mIsMovingDown = new AtomicBoolean(false);
        mClamp = true;
    }

    @Override
    public void tick() {
        if( mClamp) {
            mGameObject.setxPosition(
                    Game.clamp(mGameObject.getxPosition() + mGameObject.getxVelocity(),
                            0,
                            Game.WIDTH - mGameObject.getWidth()));
            mGameObject.setyPosition(
                    Game.clamp(mGameObject.getyPosition() + mGameObject.getyVelocity(),
                            0,
                            Game.HEIGHT - mGameObject.getHeight()));
        } else {
            mGameObject.setxPosition( mGameObject.getxPosition() + mGameObject.getxVelocity() );
            mGameObject.setyPosition( mGameObject.getyPosition() + mGameObject.getyVelocity() );
        }


    }

    @Override
    public void render(Graphics g) throws IllegalStateException {
        //Do nothing
    }

    public void move(MovementDirection direction) {
        switch(direction) {
            case UP:
                mIsMovingUp.compareAndSet(false, true);
                mGameObject.setyVelocity( -1 * mGameObject.getSpeed() );
                verticalState = MovementDirection.UP;
                break;
            case DOWN:
                mIsMovingDown.compareAndSet(false, true);
                mGameObject.setyVelocity(mGameObject.getSpeed());
                verticalState = MovementDirection.DOWN;
                break;
            case LEFT:
                mIsMovingLeft.compareAndSet(false, true);
                mGameObject.setxVelocity(-1 * mGameObject.getSpeed());
                horizontalState = MovementDirection.LEFT;
                break;
            case RIGHT:
                mIsMovingRight.compareAndSet(false, true);
                mGameObject.setxVelocity(mGameObject.getSpeed());
                horizontalState = MovementDirection.RIGHT;
                break;
            case NONE:
                break;
        }

    }

    public void stopMoving(MovementDirection direction) {
        switch(direction) {
            case UP:
                if ( !mIsMovingDown.get() ){
                    mGameObject.setyVelocity(0);
                    verticalState = MovementDirection.NONE;
                }else if( mIsMovingDown.get() ) {
                    mGameObject.setyVelocity( mGameObject.getSpeed());
                    verticalState = MovementDirection.DOWN;
                }
                mIsMovingUp.compareAndSet(true, false);
                break;
            case DOWN:
                if ( !mIsMovingUp.get() ){
                    mGameObject.setyVelocity(0);
                    verticalState = MovementDirection.NONE;
                }else if( mIsMovingUp.get() ) {
                    mGameObject.setyVelocity( -1 * mGameObject.getSpeed() );
                    verticalState = MovementDirection.UP;
                }
                mIsMovingDown.compareAndSet(true, false);
                break;
            case LEFT:
                if( !mIsMovingRight.get() ) {
                    mGameObject.setxVelocity(0);
                    horizontalState = MovementDirection.NONE;
                } else if ( mIsMovingRight.get() ) {
                    mGameObject.setxVelocity( mGameObject.getSpeed() );
                    horizontalState = MovementDirection.RIGHT;
                }
                mIsMovingLeft.compareAndSet(true, false);
                break;
            case RIGHT:
                if( !mIsMovingLeft.get() ) {
                    mGameObject.setxVelocity(0);
                    horizontalState = MovementDirection.NONE;
                } else if ( mIsMovingLeft.get() ) {
                    mGameObject.setxVelocity( -1 * mGameObject.getSpeed() );
                    horizontalState = MovementDirection.LEFT;
                }
                mIsMovingRight.compareAndSet(true, false);
                break;
            case NONE:
                break;
        }

    }

    public MovementDirection getVerticalState() {
        return verticalState;
    }

    public MovementDirection getHorizontalState() {
        return horizontalState;
    }

    public void setClamp( boolean isClamped ) {
        mClamp = isClamped;
    }
}
