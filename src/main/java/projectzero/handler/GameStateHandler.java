package main.java.projectzero.handler;
import main.java.projectzero.game.GameState;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kristianhfischer on 9/5/15.
 */
public class GameStateHandler {

    private final static GameStateHandler INSTANCE = new GameStateHandler();
    public static GameStateHandler getInstance() { return INSTANCE; }

    public enum State {
        PLAY,
        PAUSE;
    }

    private Map<State, main.java.projectzero.game.GameState> mGameStateMap;

    private GameStateHandler( ) {
        mGameStateMap = new HashMap<>();
    }

    public GameState getGameState( State state ) {
        return mGameStateMap.get(state);
    }

    public void addGameState( State state, GameState gameState) {
        mGameStateMap.put(state, gameState);

    }

}
