package com.kristianfischer.projectzero.game;

import com.kristianfischer.projectzero.command.*;
import com.kristianfischer.projectzero.component.CollisionComponent;
import com.kristianfischer.projectzero.component.MovementComponent;
import com.kristianfischer.projectzero.gameinput.KeyInput;
import com.kristianfischer.projectzero.gameinput.KeyMapper;
import com.kristianfischer.projectzero.gameobject.Laser;
import com.kristianfischer.projectzero.gameobject.Player;
import com.kristianfischer.projectzero.gameobject.attributes.Hitbox;
import com.kristianfischer.projectzero.handler.CollisionHandler;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;
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
    public static final double NUMBER_OF_TICKS = 60;

    private int debugInt = 0;

    private boolean mRunning = false;
    private Thread mGameThread;
    private GameHandler mGameHandler;
    private KeyMapper mKeyMapper;

    public Game() {
        mGameHandler = GameHandler.getInstance();
        mKeyMapper = new KeyMapper();
        mKeyMapper.setKeyMapping(KeyEvent.VK_W, new MoveUpCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_A, new MoveLeftCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_D, new MoveRightCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_S, new MoveDownCommand());
        mKeyMapper.setKeyMapping(KeyEvent.VK_SPACE, new FireCommand());
        this.addKeyListener(new KeyInput(mGameHandler, mKeyMapper));
        new GameWindow(WIDTH, HEIGHT, "Space Invaders Clone!", this);
        Player player = new Player.Builder()
                .xPosition(100)
                .yPosition(100)
                .gameId(GameId.PLAYER)
                .speed(5)
                .isActive(true)
                .movementComponent(new MovementComponent())
                .collisionComponent(new CollisionComponent())
                .build();
        player.getCollisionComponent().setHitbox(new Hitbox.Builder(player)
                .rectangle(new Rectangle(player.getxPosition(),
                        player.getyPosition(),
                        player.RENDER_WIDTH,
                        player.RENDER_HEIGHT))
                .build());
        mGameHandler.addGameObject(player);


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
                detectCollisions();
                spawnNewObjects();
                deleteDestroyedObjects();
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
        if( debugInt % 200 == 0) {
            Laser projectile = new Laser.Builder()
                .xPosition(WIDTH / 2)
                .yPosition(1)
                .gameId(GameId.ENEMY_PROJECTILE)
                .speed(5)
                .isActive(true)
                .collisionComponent(new CollisionComponent())
                .build();
        projectile.getCollisionComponent().setHitbox(new Hitbox.Builder(projectile)
                .rectangle(new Rectangle(projectile.getxPosition(),
                        projectile.getyPosition(), Laser.RENDER_WIDTH, Laser.RENDER_HEIGHT))
                .build());
        projectile.setyVelocity( projectile.getSpeed() );
        DynamicGameObjectHandler.getInstance().addNewGameObject(projectile);
        }
        //System.out.println(debugInt);
        debugInt++;
        if( debugInt == Integer.MAX_VALUE) {
            debugInt = 0;
        }
        while( DynamicGameObjectHandler.getInstance().hasNextNewGameObject() ) {
            mGameHandler.addGameObject( DynamicGameObjectHandler.getInstance().getNextNewGameObject() );
        }
    }

    private void deleteDestroyedObjects() {
        while( DynamicGameObjectHandler.getInstance().hasNextDestroyedGameObject() ) {
            mGameHandler.removeGameObject(DynamicGameObjectHandler.getInstance().getNextDestroyedGameObject());
        }
    }

    private void detectCollisions() {
        CollisionHandler.getInstance().handleCollisions(mGameHandler);
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
