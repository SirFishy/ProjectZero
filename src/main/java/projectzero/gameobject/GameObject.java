package main.java.projectzero.gameobject;

import main.java.projectzero.component.*;
import main.java.projectzero.game.GameId;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public abstract class GameObject {

    protected int xPosition, yPosition;
    protected int xVelocity, yVelocity;
    protected int width;
    protected int height;
    protected int speed;
    protected boolean isActive;
    protected boolean isDestroyed;
    protected List<IGameComponent> componentList;
    protected CollisionGameComponent collisionComponent;
    protected MovementGameComponent movementComponent;
    protected AnimationGameComponent animationComponent;
    protected GameId gameId;

    public static abstract class AbstractBuilder<T> {
        private int xPosition = 0;
        private int yPosition = 0;
        private int xVelocity = 0;
        private int yVelocity = 0;
        private int width = 0;
        private int height = 0;
        private int speed = 0;
        private boolean isActive = false;
        private GameId gameId = GameId.NONE;
        private List<IGameComponent> componentList = new ArrayList<>();
        private CollisionGameComponent collisionComponent;
        private MovementGameComponent movementComponent;
        private AnimationGameComponent animationComponent;
        protected abstract T self();
        public T xPosition( int xPosition ) { this.xPosition = xPosition; return self(); }
        public T yPosition( int yPosition ) { this.yPosition = yPosition; return self(); }
        public T width( int width ) { this.width = width; return self(); }
        public T height( int height ) { this.height = height; return self(); }
        public T xVelocity( int xVelocity ) { this.xVelocity = xVelocity; return self(); }
        public T yVelocity( int yVelocity ) { this.yVelocity = yVelocity; return self(); }
        public T isActive( boolean isActive ) { this.isActive = isActive; return  self(); }
        public T gameId( GameId gameId ) { this.gameId = gameId; return self(); }
        public T speed( int speed ) { this.speed = speed; return self(); }
        public T collisionComponent( CollisionGameComponent collisionComponent )
            { this.collisionComponent = collisionComponent; this.componentList.add( collisionComponent ); return self(); }
        public T movementComponent( MovementGameComponent movementComponent )
            { this.movementComponent = movementComponent; this.componentList.add( movementComponent ); return self(); }
        public T animationComponent( AnimationGameComponent animationComponent )
        { this.animationComponent = animationComponent; this.componentList.add( animationComponent ); return self(); }
    }

    public GameObject( AbstractBuilder builder ) {
        this.xPosition = builder.xPosition;
        this.yPosition = builder.yPosition;
        this.width = builder.width;
        this.height = builder.height;
        this.gameId = builder.gameId;
        this.xVelocity = builder.xVelocity;
        this.yVelocity = builder.yVelocity;
        this.speed = builder.speed;
        this.isActive = builder.isActive;
        this.collisionComponent = builder.collisionComponent;
        this.movementComponent = builder.movementComponent;
        this.animationComponent = builder.animationComponent;
        this.componentList = builder.componentList;
        isDestroyed = false;
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

    public int getSpeed() { return speed; }

    public void setSpeed(int speed) { this.speed = speed; }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {

        return isActive;
    }

    public CollisionGameComponent getCollisionComponent() {
        return collisionComponent;
    }

    public MovementGameComponent getMovementComponent() {
        return movementComponent;
    }

    public AnimationGameComponent getAnimationComponent() {
        return animationComponent;
    }

    public List<IGameComponent> getGameComponents() { return componentList; }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setIsDestroyed( boolean isDestroyed ) { this.isDestroyed = isDestroyed; }

    public boolean isDestroyed() { return isDestroyed; }
}
