package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.component.CollisionComponent;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.attributes.Hitbox;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public abstract class GameObject {

    protected int xPosition, yPosition;
    protected int xVelocity, yVelocity;
    protected int speed;
    protected CollisionComponent collisionComponent;
    protected GameId gameId;



    public static abstract class AbstractBuilder<T> {
        private int xPosition = 0;
        private int yPosition = 0;
        private int xVelocity = 0;
        private int yVelocity = 0;
        private int speed = 0;
        private GameId gameId = GameId.None;
        private CollisionComponent collisionComponent = null;
        protected abstract T self();
        public T xPosition( int xPosition ) { this.xPosition = xPosition; return self(); }
        public T yPosition( int yPosition ) { this.yPosition = yPosition; return self(); }
        public T xVelocity( int xVelocity ) { this.xVelocity = xVelocity; return self(); }
        public T yVelocity( int yVelocity ) { this.yVelocity = yVelocity; return self(); }
        public T gameId( GameId gameId ) { this.gameId = gameId; return self(); }
        public T speed( int speed ) { this.speed = speed; return self(); }
        public T collisionComponent( CollisionComponent collisionComponent )
            { this.collisionComponent = collisionComponent; return self(); }
    }

    public GameObject( AbstractBuilder builder ) {
        this.xPosition = builder.xPosition;
        this.yPosition = builder.yPosition;
        this.gameId = builder.gameId;
        this.xVelocity = builder.xVelocity;
        this.yVelocity = builder.yVelocity;
        this.speed = builder.speed;
        this.collisionComponent = builder.collisionComponent;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public GameId getGameId() {
        return gameId;
    }

    public void setGameId(GameId gameId) {
        this.gameId = gameId;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
