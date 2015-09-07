package main.java.projectzero.game;

import main.java.projectzero.gameinput.MenuMouseListener;
import main.java.projectzero.handler.GameStateHandler;
import main.java.projectzero.menu.*;
import main.java.projectzero.menu.Menu;

import java.awt.*;

/**
 * Created by kristianhfischer on 9/4/15.
 */
public class PauseGameState extends GameState {

    private boolean mStateSwitched;
    private MenuMouseListener mMouseListener;
    private Menu mPauseMenu;

    public PauseGameState(Game game) {
        super(game);
        mStateSwitched = true;
        mPauseMenu = new PauseMenu();
    }

    @Override
    public void tick() {
        checkState();
        mPauseMenu.tick(mGame.getMousePosition());
    }

    @Override
    public void render(Graphics g) {
        mPauseMenu.render(g);

    }

    private void checkState() {

        if(mStateSwitched) {
            mStateSwitched = false;
            initializeListeners();
            registerListeners();
        }

        if( !Game.PAUSE_GAME ) {
            mStateSwitched = true;
            unregisterListeners();
            mGame.setState(GameStateHandler.getInstance().getGameState(GameStateHandler.State.PLAY));
        }

    }

    private void initializeListeners() {

        //Set keys up for menu
        //mKeyMapper = new KeyMapper();
        //mKeyMapper.setKeyMapping(KeyEvent.VK_ESCAPE, new PauseCommand());

        //Create player key input
        //mKeyInput = new KeyInput(mKeyMapper);
        mMouseListener = new MenuMouseListener(mPauseMenu);


    }

    private void registerListeners() {
        //mGame.addKeyListener(mKeyInput);
        mGame.addMouseListener(mMouseListener);
    }

    private void unregisterListeners() {
       // mGame.removeKeyListener(mKeyInput);
        mGame.removeMouseListener(mMouseListener);
    }
}
