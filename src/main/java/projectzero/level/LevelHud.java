package main.java.projectzero.level;

import main.java.projectzero.game.Game;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/29/15.
 */
public class LevelHud {

    private static LevelHud INSTANCE = new LevelHud();
    private int mNumberOfLives;
    private int mScore;

    public static LevelHud getInstance() {
        return INSTANCE;
    }

    private LevelHud() {
        mNumberOfLives = 3;
        mScore = 0;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        Font font = new Font("Dialog", Font.PLAIN, 14);
        g.setFont(font);

        g.setColor(Color.white);
        g.drawString("Score: " + mScore, 5, 15);
        g.drawString("Lives: " + mNumberOfLives, Game.WIDTH - 80, 15);
    }

    public void addScore( int score ) {
        mScore += score;
    }

    public void clear() {
        mScore = 0;
        mNumberOfLives = 3;
    }

    public int getNumberOfLives() {
        return mNumberOfLives;
    }

    public void addLife() {
        mNumberOfLives ++;
    }

    public void removeLife() {
        mNumberOfLives--;
    }
}
