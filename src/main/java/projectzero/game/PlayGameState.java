package main.java.projectzero.game;

import main.java.projectzero.command.FireCommand;
import main.java.projectzero.command.MoveLeftCommand;
import main.java.projectzero.command.MoveRightCommand;
import main.java.projectzero.command.PauseCommand;
import main.java.projectzero.gameinput.KeyInput;
import main.java.projectzero.gameinput.KeyMapper;
import main.java.projectzero.handler.*;
import main.java.projectzero.level.LevelHud;
import main.java.projectzero.level.LevelOne;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by kristianhfischer on 8/15/15.
 */
public class PlayGameState extends GameState {

    private GameHandler mGameHandler;
    private LevelOne mLevelOne;
    private KeyMapper mKeyMapper;
    private KeyInput mKeyInput;
    private boolean mStateSwitched;

    public PlayGameState( Game game ) {
        super(game);
        mStateSwitched = true;

        mGameHandler = GameHandler.getInstance();

        //Generate and build level
        mLevelOne = new LevelOne(mGameHandler);
        mLevelOne.build();

    }

    @Override
    public void tick() {
        checkState();
        mGameHandler.tick();
        UfoHandler.getInstance().update();
        spawnNewObjects();
        detectCollisions();
        deleteDestroyedObjects();
        handleTheHive();
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

    private void checkState() {
        if( mStateSwitched ) {
            mStateSwitched = false;
            initializeListeners();
            registerListeners();
        }

        if( Game.PAUSE_GAME ) {
            unregisterListeners();
            mStateSwitched = true;
            mGame.setState( GameStateHandler.getInstance().getGameState(GameStateHandler.State.PAUSE));
            return;
        }
    }

    private void initializeListeners() {
        mKeyMapper = new KeyMapper();
        mKeyMapper.setKeyMapping(KeyEvent.VK_A, new MoveLeftCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_D, new MoveRightCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_SPACE, new FireCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_ESCAPE, new PauseCommand());

        //Create player key input
        mKeyInput = new KeyInput(mKeyMapper);
    }

    private void registerListeners() {
        mGame.addKeyListener(mKeyInput);
    }

    private void unregisterListeners() {
        mGame.removeKeyListener(mKeyInput);
    }


}
