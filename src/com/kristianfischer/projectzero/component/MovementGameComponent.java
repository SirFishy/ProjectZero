package com.kristianfischer.projectzero.component;

import com.kristianfischer.projectzero.game.Game;
import com.kristianfischer.projectzero.gameobject.GameObject;

/**
 * Created by kristianhfischer on 7/13/15.
 */
public class MovementGameComponent extends GameComponent {

    private MovementDirection verticalState;
    private MovementDirection horizontalState;
    private int mNumHorizontalButtonsPressed;
    private int mNumVerticalButtonsPressed;
    private GameObject mGameObject;

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
        mNumHorizontalButtonsPressed = 0;
        mNumVerticalButtonsPressed = 0;
    }

    @Override
    public void update( ) {
        mGameObject.setxPosition(
                Game.clamp(mGameObject.getxPosition() + mGameObject.getxVelocity(),
                        0,
                        Game.WIDTH - mGameObject.getWidth()));
        mGameObject.setyPosition(
                Game.clamp(mGameObject.getyPosition() + mGameObject.getyVelocity(),
                        0,
                        Game.HEIGHT - mGameObject.getHeight() ));

    }

    public void move(MovementDirection direction) {
        switch(direction) {
            case UP:
                mNumVerticalButtonsPressed ++;
                mGameObject.setyVelocity( -1 * mGameObject.getSpeed() );
                verticalState = MovementDirection.UP;
                break;
            case DOWN:
                mNumVerticalButtonsPressed ++;
                mGameObject.setyVelocity(mGameObject.getSpeed());
                verticalState = MovementDirection.DOWN;
                break;
            case LEFT:
                mNumHorizontalButtonsPressed++;
                mGameObject.setxVelocity(-1 * mGameObject.getSpeed());
                horizontalState = MovementDirection.LEFT;
                break;
            case RIGHT:
                mNumHorizontalButtonsPressed++;
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
                mNumVerticalButtonsPressed--;
                if (verticalState == MovementDirection.UP && mNumVerticalButtonsPressed == 0){
                    mGameObject.setyVelocity(0);
                    verticalState = MovementDirection.NONE;
                }else if( mNumVerticalButtonsPressed > 0 ) {
                    mGameObject.setyVelocity( mGameObject.getSpeed());
                    verticalState = MovementDirection.DOWN;
                }
                break;
            case DOWN:
                mNumVerticalButtonsPressed --;
                if (verticalState == MovementDirection.DOWN && mNumVerticalButtonsPressed == 0){
                    mGameObject.setyVelocity(0);
                    verticalState = MovementDirection.NONE;
                }else if( mNumVerticalButtonsPressed > 0 ) {
                    mGameObject.setyVelocity( -1 * mGameObject.getSpeed() );
                    verticalState = MovementDirection.UP;
                }

                break;
            case LEFT:
                mNumHorizontalButtonsPressed --;
                if( horizontalState == MovementDirection.LEFT && mNumHorizontalButtonsPressed == 0) {
                    mGameObject.setxVelocity(0);
                    horizontalState = MovementDirection.NONE;
                } else if ( mNumHorizontalButtonsPressed > 0 ) {
                    mGameObject.setxVelocity( mGameObject.getSpeed() );
                    horizontalState = MovementDirection.RIGHT;
                }

                break;
            case RIGHT:
                mNumHorizontalButtonsPressed --;
                if( horizontalState == MovementDirection.RIGHT && mNumHorizontalButtonsPressed == 0) {
                    mGameObject.setxVelocity(0);
                    horizontalState = MovementDirection.NONE;
                } else if ( mNumHorizontalButtonsPressed > 0 ) {
                    mGameObject.setxVelocity( -1 * mGameObject.getSpeed() );
                    horizontalState = MovementDirection.LEFT;
                }
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
}
