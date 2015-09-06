package main.java.projectzero.menu;

import main.java.projectzero.game.Game;
import main.java.projectzero.menu.ui.GameButton;

import java.awt.*;

/**
 * Created by kristianhfischer on 9/5/15.
 */
public class PauseMenu implements Menu {

    GameButton mResumeGameButton;

    private boolean pauseGame;

    public PauseMenu() {
        pauseGame = true;
        mResumeGameButton = new GameButton(Game.WIDTH / 2 - 100, 60, 200, 50);
        mResumeGameButton.setButtonText("Resume Game");
        mResumeGameButton.setxTextOffset(50);
        mResumeGameButton.setyTextOffset(30);
        mResumeGameButton.setButtonListener(new GameButton.IButtonListener() {
            @Override
            public void onButtonReleased() {
                //Unpause game?
                pauseGame = false;
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
    }

    @Override
    public void tick( Point point ) {

        if( !pauseGame ) {
            mResumeGameButton.reset();
            pauseGame = true;
            Game.PAUSE_GAME = false;
        } else if( Game.PAUSE_GAME ) {
            mResumeGameButton.hover(point);
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
    }

    @Override
    public void clickPress(Point point) {
        mResumeGameButton.press(point);
    }

    @Override
    public void clickRelease(Point point) {
        mResumeGameButton.release(point);
    }
}
