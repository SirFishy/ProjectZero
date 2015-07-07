package com.kristianfischer.projectzero.game;

import com.kristianfischer.projectzero.gameobject.Player;

import java.awt.*;
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

    public Game() {
        new GameWindow(WIDTH, HEIGHT, "Space Invaders Clone!", this);
        mGameHandler = new GameHandler();
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
