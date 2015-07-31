package com.kristianfischer.projectzero.game;

import com.kristianfischer.projectzero.command.FireCommand;
import com.kristianfischer.projectzero.command.MoveLeftCommand;
import com.kristianfischer.projectzero.command.MoveRightCommand;
import com.kristianfischer.projectzero.gameinput.KeyInput;
import com.kristianfischer.projectzero.gameinput.KeyMapper;
import com.kristianfischer.projectzero.handler.CollisionHandler;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;
import com.kristianfischer.projectzero.handler.GameHandler;
import com.kristianfischer.projectzero.handler.HiveHandler;
import com.kristianfischer.projectzero.level.LevelHud;
import com.kristianfischer.projectzero.level.LevelOne;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 800;
    public static final int ACTUAL_HEIGHT = WIDTH /16 * 9;
    public static final int HEIGHT_PADDING = 25;
    public static final int HEIGHT = ACTUAL_HEIGHT - HEIGHT_PADDING;
    public static final double NUMBER_OF_TICKS = 60;

    private boolean mRunning = false;
    private Thread mGameThread;
    private GameHandler mGameHandler;
    private KeyMapper mKeyMapper;
    LevelOne mLevelOne;

    public Game() {
        mGameHandler = GameHandler.getInstance();
        mKeyMapper = new KeyMapper();
        //mKeyMapper.setKeyMapping(KeyEvent.VK_W, new MoveUpCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_A, new MoveLeftCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_D, new MoveRightCommand());
        //mKeyMapper.setKeyMapping(KeyEvent.VK_S, new MoveDownCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_SPACE, new FireCommand());
        mLevelOne = new LevelOne(mGameHandler);
        mLevelOne.build();
        this.addKeyListener(new KeyInput(mGameHandler, mKeyMapper));
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

    public static int clamp( int value, int valueMin, int valueMax) {
        if( value < valueMin )
            return valueMin;
        if( value > valueMax )
            return valueMax;
        return value;
    }

    private void tick() {
        mGameHandler.tick();
        spawnNewObjects();
        detectCollisions();
        deleteDestroyedObjects();
        handleTheHive();
    }
    private void spawnNewObjects() {
        while( DynamicGameObjectHandler.getInstance().hasNextNewGameObject() ) {
            mGameHandler.addGameObject( DynamicGameObjectHandler.getInstance().getNextNewGameObject() );
        }
    }

    private void deleteDestroyedObjects() {
        while( DynamicGameObjectHandler.getInstance().hasNextDestroyedGameObject() ) {
            mGameHandler.removeGameObject(DynamicGameObjectHandler.getInstance().getNextDestroyedGameObject());
        }
    }
    private void detectCollisions() { CollisionHandler.getInstance().handleCollisions(mGameHandler);
     }
    private void handleTheHive() { HiveHandler.getInstance().updateHiveCommands();
     }

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
