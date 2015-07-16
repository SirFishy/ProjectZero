package com.kristianfischer.projectzero.game;

import com.kristianfischer.projectzero.command.*;
import com.kristianfischer.projectzero.gameinput.KeyInput;
import com.kristianfischer.projectzero.gameinput.KeyMapper;
import com.kristianfischer.projectzero.gameobject.Player;
import com.kristianfischer.projectzero.handler.DynamicObjectHandler;
import com.kristianfischer.projectzero.handler.GameHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 12 * 9;

    private boolean mRunning = false;
    private Thread mGameThread;
    private GameHandler mGameHandler;
    private KeyMapper mKeyMapper;

    public Game() {
        mGameHandler = new GameHandler();
        mKeyMapper = new KeyMapper();
        mKeyMapper.setKeyMapping(KeyEvent.VK_W, new MoveUpCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_A, new MoveLeftCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_D, new MoveRightCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_S, new MoveDownCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_SPACE, new FireCommand());
        this.addKeyListener(new KeyInput(mGameHandler, mKeyMapper));
        new GameWindow(WIDTH, HEIGHT, "Space Invaders Clone!", this);
        mGameHandler.addGameObject(new Player(100, 100, GameId.Player));

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
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while( mRunning ) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                spawnNewObjects();
                delta--;
            }

            if( mRunning ) {
                render();
            }

            frames++;

            if( System.currentTimeMillis() - timer > 1000 ) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public static void main(String args[]) {
        new Game();
    }

    private void tick() {
        mGameHandler.tick();
    }

    private void spawnNewObjects() {
        while( DynamicObjectHandler.getInstance().hasNextGameObject() ) {
            mGameHandler.addGameObject( DynamicObjectHandler.getInstance().getNextGameObject() );
        }
    }

    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bufferStrategy.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        mGameHandler.render(g);
        g.dispose();
        bufferStrategy.show();

    }



}
