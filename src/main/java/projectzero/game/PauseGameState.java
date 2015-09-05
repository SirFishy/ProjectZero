package main.java.projectzero.game;

import main.java.projectzero.handler.GameStateHandler;

import java.awt.*;

/**
 * Created by kristianhfischer on 9/4/15.
 */
public class PauseGameState extends GameState {

    public PauseGameState(Game game) {
        super(game);
    }

    @Override
    public void tick() {
        if( !Game.PAUSE_GAME ) {
            mGame.setState(GameStateHandler.getInstance().getGameState(GameStateHandler.State.PLAY));
        }
    }

    @Override
    public void render(Graphics g) {

    }
}
