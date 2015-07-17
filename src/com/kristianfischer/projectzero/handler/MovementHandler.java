package com.kristianfischer.projectzero.handler;

import com.kristianfischer.projectzero.gameobject.GameObject;

/**
 * Created by kristianhfischer on 7/13/15.
 */
public class MovementHandler {

    private MovementDirection verticalState;
    private MovementDirection horizontalState;
    private int mNumHorizontalButtonsPressed;
    private int mNumVerticalButtonsPressed;
    private GameObject object;

    public enum MovementDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE;
    }

    public MovementHandler(GameObject object) {
        verticalState = MovementDirection.NONE;
        horizontalState = MovementDirection.NONE;
        mNumHorizontalButtonsPressed = 0;
        mNumVerticalButtonsPressed = 0;
        this.object = object;
    }

    public void move(MovementDirection direction) {
        switch(direction) {
            case UP:
                object.setyVelocity( -1 * object.getSpeed() );
                verticalState = MovementDirection.UP;
                mNumVerticalButtonsPressed ++;
                break;
            case DOWN:
                object.setyVelocity(object.getSpeed());
                verticalState = MovementDirection.DOWN;
                mNumVerticalButtonsPressed ++;
                break;
            case LEFT:
                object.setxVelocity(-1 * object.getSpeed());
                horizontalState = MovementDirection.LEFT;
                mNumHorizontalButtonsPressed++;
                break;
            case RIGHT:
                object.setxVelocity(object.getSpeed());
                horizontalState = MovementDirection.RIGHT;
                mNumHorizontalButtonsPressed++;
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
                    object.setyVelocity(0);
                    verticalState = MovementDirection.NONE;
                }else if( mNumVerticalButtonsPressed > 0 ) {
                    object.setyVelocity( object.getSpeed());
                    verticalState = MovementDirection.DOWN;
                }
                break;
            case DOWN:
                mNumVerticalButtonsPressed --;
                if (verticalState == MovementDirection.DOWN && mNumVerticalButtonsPressed == 0){
                    object.setyVelocity(0);
                    verticalState = MovementDirection.NONE;
                }else if( mNumVerticalButtonsPressed > 0 ) {
                    object.setyVelocity( -1 * object.getSpeed() );
                    verticalState = MovementDirection.UP;
                }

                break;
            case LEFT:
                mNumHorizontalButtonsPressed --;
                if( horizontalState == MovementDirection.LEFT && mNumHorizontalButtonsPressed == 0) {
                    object.setxVelocity(0);
                    horizontalState = MovementDirection.NONE;
                } else if ( mNumHorizontalButtonsPressed > 0 ) {
                    object.setxVelocity( object.getSpeed() );
                    horizontalState = MovementDirection.RIGHT;
                }

                break;
            case RIGHT:
                mNumHorizontalButtonsPressed --;
                if( horizontalState == MovementDirection.RIGHT && mNumHorizontalButtonsPressed == 0) {
                    object.setxVelocity(0);
                    horizontalState = MovementDirection.NONE;
                } else if ( mNumHorizontalButtonsPressed > 0 ) {
                    object.setxVelocity( -1 * object.getSpeed() );
                    horizontalState = MovementDirection.LEFT;
                }
                break;
            case NONE:
                break;
        }





    }
}
