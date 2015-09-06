package main.java.projectzero.menu;

import java.awt.*;

/**
 * Created by kristianhfischer on 9/5/15.
 */
public interface Menu {

    void tick(Point point);
    void render(Graphics g);
    void clickPress(Point point);
    void clickRelease(Point point);
}
