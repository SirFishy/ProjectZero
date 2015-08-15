package main.java.projectzero.game;

import main.java.projectzero.command.FireCommand;
import main.java.projectzero.command.MoveLeftCommand;
import main.java.projectzero.command.MoveRightCommand;
import main.java.projectzero.gameinput.KeyInput;
import main.java.projectzero.gameinput.KeyMapper;
import main.java.projectzero.handler.*;
import main.java.projectzero.level.LevelHud;
import main.java.projectzero.level.LevelOne;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public class Game extends Canvas implements Runnable{

    /**
     *  Width of the game window
     */
    public static final int WIDTH = 800;

    /**
     *  Height of the game window
     */
    public static final int ACTUAL_HEIGHT = WIDTH /16 * 9;

    /**
     * Padding adjustment for the game window:
     * This value is manually adjusted so that objects can't render outside of the
     * canvas area.
     */
    public static final int HEIGHT_PADDING = 25;

    /**
     *  The manually set max height that objects can render in the game window
     */
    public static final int HEIGHT = ACTUAL_HEIGHT - HEIGHT_PADDING;

    /**
     * If enemies reach the GAME_OVER_LINE, then the player loses
     */
    public static final int GAME_OVER_LINE = HEIGHT - 32;

    /**
     *  Ticks, or updates, per second
     */
    public static final double NUMBER_OF_TICKS = 60;

    /**
     * Drives game thread
     */
    private boolean mRunning = false;

    private Thread mGameThread;
    private GameHandler mGameHandler;
    private KeyMapper mKeyMapper;
    private LevelOne mLevelOne;

    public Game() {
        mGameHandler = GameHandler.getInstance();
        //Set player key bindings
        mKeyMapper = new KeyMapper();
        mKeyMapper.setKeyMapping(KeyEvent.VK_A, new MoveLeftCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_D, new MoveRightCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_SPACE, new FireCommand());

        //Generate and build level
        mLevelOne = new LevelOne(mGameHandler);
        mLevelOne.build();

        //Register player key input
        this.addKeyListener(new KeyInput(mGameHandler, mKeyMapper));

        //Create game window
        new GameWindow(WIDTH, ACTUAL_HEIGHT, "Space Invaders Clone!", this);

    }

    public synchronized void start() {
        mGameThread = new Thread(this);
        mGameThread.start();
        mRunning = true;

    }

    public synchronized void stop() {
        try {
            mGameThread.join();
            mRunning = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method contains the Game loop.
     */
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double ns = 1000000000 / NUMBER_OF_TICKS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while( mRunning ) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }

            if( mRunning ) {
                render();
            }

            /*frames++;

            if( System.currentTimeMillis() - timer > 1000 ) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }*/
        }
        stop();
    }

    public static void main(String args[]) {
        new Game();
    }

    /**
     * clamp is a utility method that will restrict a value from being less than valueMin or greater
     * than valueMax
     * @param value - the current value
     * @param valueMin - the minimum bound for the value
     * @param valueMax - the maximum bound for the value
     * @return  valueMin if value < valueMin
     *          valueMax if value > valueMax
     *          value if valueMin <= value <= valueMax
     */
    public static int clamp( int value, int valueMin, int valueMax) {
        if( value < valueMin )
            return valueMin;
        if( value > valueMax )
            return valueMax;
        return value;
    }

    /**
     * tick updates all of the game's objects in the game loop
     */
    private void tick() {
        mGameHandler.tick();
        UfoHandler.getInstance().update();
        spawnNewObjects();
        detectCollisions();
        deleteDestroyedObjects();
        handleTheHive();
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

    /**
     * render will use a double buffer to render all of the game's objects
     */
    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null) {
            this.createBufferStrategy(2);
            return;
        }
        Graphics g = bufferStrategy.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, ACTUAL_HEIGHT);
        g.setColor(Color.green);
        g.fillRect(0, HEIGHT - 10, WIDTH, HEIGHT);
        LevelHud.getInstance().render(g);
        mGameHandler.render(g);
        g.dispose();
        bufferStrategy.show();
    }



}
