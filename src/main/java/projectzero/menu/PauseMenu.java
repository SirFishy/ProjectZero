package main.java.projectzero.menu;

import main.java.projectzero.game.Game;
import main.java.projectzero.game.PlayGameState;
import main.java.projectzero.handler.GameStateHandler;
import main.java.projectzero.menu.ui.GameButton;

import java.awt.*;

/**
 * Created by kristianhfischer on 9/5/15.
 */
public class PauseMenu implements Menu {

    private GameButton mResumeGameButton;
    private GameButton mRestartGameButton;
    private boolean mUnpausing;

    private static final int BUTTON_XPOSITION = Game.WIDTH / 2 - 100;
    private static final int BUTTON_YPOSITION = 60;
    private static final int BUTTON_SPACING = 60;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_SPEED = 50;

    public PauseMenu() {
        initializeButtons();
        mUnpausing = false;
    }

    @Override
    public void tick( Point point ) {

        if( Game.PAUSE_GAME ) {
            mResumeGameButton.tick(point);
            mRestartGameButton.tick(point);
        } else if( !Game.PAUSE_GAME ) {
            initializeButtons();
        }

        if( mResumeGameButton.getxPosition() == BUTTON_XPOSITION ) {
            mResumeGameButton.setxVelocity(0);
            mRestartGameButton.setxVelocity(0);
        } else if( mResumeGameButton.getxPosition() > Game.WIDTH ) {
            Game.PAUSE_GAME = false;
        }


    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTH, Game.ACTUAL_HEIGHT);
        Font font = new Font("Dialog", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("GAME PAUSED", Game.WIDTH / 2 - 64, 30);
        mResumeGameButton.render(g);
        mRestartGameButton.render(g);
    }

    @Override
    public void clickPress(Point point) {
        mResumeGameButton.press(point);
        mRestartGameButton.press(point);
    }

    @Override
    public void clickRelease(Point point) {
        mResumeGameButton.release(point);
        mRestartGameButton.release(point);
    }

    private void initializeButtons() {
        mResumeGameButton = new GameButton(0 - BUTTON_WIDTH, BUTTON_YPOSITION, BUTTON_WIDTH, BUTTON_HEIGHT);
        mResumeGameButton.setButtonText("Resume Game");
        mResumeGameButton.setxTextOffset(50);
        mResumeGameButton.setyTextOffset(30);
        mResumeGameButton.setxVelocity(BUTTON_SPEED);
        mResumeGameButton.setIsActive(true);
        mResumeGameButton.setButtonListener(new GameButton.IButtonListener() {
            @Override
            public void onButtonReleased() {
                //Unpause game?
                unpauseGame();
            }

            @Override
            public void onButtonPressed() {
                //Do Nothing
            }

            @Override
            public void onButtonHovered() {
                //Do nothing
            }
        });
        mRestartGameButton = new GameButton(0 - BUTTON_WIDTH, BUTTON_YPOSITION + BUTTON_SPACING,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        mRestartGameButton.setButtonText("Restart Game");
        mRestartGameButton.setxTextOffset(48);
        mRestartGameButton.setyTextOffset(30);
        mRestartGameButton.setxVelocity(BUTTON_SPEED);
        mRestartGameButton.setIsActive(true);
        mRestartGameButton.setButtonListener(new GameButton.IButtonListener() {

            @Override
            public void onButtonReleased() {
                unpauseGame();
                ((PlayGameState) GameStateHandler.getInstance().
                        getGameState(GameStateHandler.State.PLAY)).restartLevel();
            }

            @Override
            public void onButtonPressed() {

            }

            @Override
            public void onButtonHovered() {

            }
        });
    }

    public void unpauseGame() {

        mResumeGameButton.setxVelocity(BUTTON_SPEED);
        mRestartGameButton.setxVelocity(BUTTON_SPEED);
    }
}
