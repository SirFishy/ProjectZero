package com.kristianfischer.projectzero.gameobject;

import com.kristianfischer.projectzero.game.GameId;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public abstract class GameObject {

    protected int xPosition, yPosition;
    protected int xVelocity, yVelocity;
    protected int speed;
    protected GameId gameId;

    /**
     *
     * @param xPosition - The x-axis position of the game Object in the 2D world
     * @param yPosition - The y-axis position of the game Object in the 2D world
     * @param id - Identification of game object
     */
    public GameObject( int xPosition, int yPosition, GameId id) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        gameId = id;
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
