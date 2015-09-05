package main.java.projectzero.game;

import java.awt.*;

/**
 * Created by kristianhfischer on 8/15/15.
 */
public abstract class GameState {

    protected Game mGame;

    public GameState( Game game ) {
        mGame = game;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
}
