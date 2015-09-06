package main.java.projectzero.command;

import main.java.projectzero.game.Game;
import main.java.projectzero.gameobject.GameObject;

/**
 * Created by kristianhfischer on 9/4/15.
 */
public class PauseCommand implements ICommand {

    private boolean mPausedPressed;

    public PauseCommand() {
        mPausedPressed = false;
    }

    @Override
    public void execute(GameObject object) {

        if( Game.PAUSE_GAME && !mPausedPressed ) {
            Game.PAUSE_GAME = false;
            mPausedPressed = true;
        } else if ( !Game.PAUSE_GAME && !mPausedPressed ) {
            Game.PAUSE_GAME = true;
            mPausedPressed = true;
        }

    }

    @Override
    public void stop(GameObject object) {
        mPausedPressed = false;
    }

}
