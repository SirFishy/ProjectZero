package main.java.projectzero.gameobject.attributes;

/**
 * Created by kristianhfischer on 7/25/15.
 */
public class HitboxParameters {
    private int xOffset, yOffset;
    private int width, height;
    public HitboxParameters(int xOffset, int yOffset, int width, int height) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.width = width;
        this.height = height;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
