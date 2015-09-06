package main.java.projectzero.gameinput;

import main.java.projectzero.menu.Menu;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by kristianhfischer on 9/5/15.
 */
public class MenuMouseListener extends MouseInputAdapter {

    Menu menu;

    public MenuMouseListener( Menu menu ) {
        this.menu = menu;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        menu.clickPress(e.getPoint());
        System.out.println("Mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        menu.clickRelease(e.getPoint());
        System.out.println("Mouse released");
    }

}
