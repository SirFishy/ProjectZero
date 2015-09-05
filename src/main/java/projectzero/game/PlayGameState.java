package main.java.projectzero.game;

import main.java.projectzero.handler.*;
import main.java.projectzero.level.LevelHud;

import java.awt.*;

/**
 * Created by kristianhfischer on 8/15/15.
 */
public class PlayGameState extends GameState {

    private GameHandler mGameHandler;

    public PlayGameState( Game game ) {
        super(game);
        mGameHandler = GameHandler.getInstance();
    }

    @Override
    public void tick() {
        mGameHandler.tick();
        UfoHandler.getInstance().update();
        spawnNewObjects();
        detectCollisions();
        deleteDestroyedObjects();
        handleTheHive();
        if( Game.PAUSE_GAME ) {
            mGame.setState( GameStateHandler.getInstance().getGameState(GameStateHandler.State.PAUSE));
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTH, Game.ACTUAL_HEIGHT);
        g.setColor(Color.green);
        g.fillRect(0, Game.HEIGHT - 10, Game.WIDTH, Game.HEIGHT);
        LevelHud.getInstance().render(g);
        mGameHandler.render(g);
    }

    /**
     * Helper method to spawn new objects created in the game loop
     */
    private void spawnNewObjects() {
        while( DynamicGameObjectHandler.getInstance().hasNextNewGameObject() ) {
            mGameHandler.addGameObject( DynamicGameObjectHandler.getInstance().getNextNewGameObject() );
        }
    }

    /**
     * Helper method to remove objects destroyed in the game loop
     */
    private void deleteDestroyedObjects() {
        while( DynamicGameObjectHandler.getInstance().hasNextDestroyedGameObject() ) {
            mGameHandler.removeGameObject(DynamicGameObjectHandler.getInstance().getNextDestroyedGameObject());
        }
    }

    /**
     * Helper method to detect collisions between objects in the game loop
     */
    private void detectCollisions() { CollisionHandler.getInstance().handleCollisions(mGameHandler);
    }

    /**
     * Helper method to handle enemy behavior in the game loop
     */
    private void handleTheHive() { HiveHandler.getInstance().updateHiveCommands();
    }
}
