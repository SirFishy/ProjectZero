package main.java.projectzero.game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kristianhfischer on 7/6/15.
 */
public class GameWindow extends Canvas {


    public GameWindow(int width, int height, String title, Game game) {
        JFrame jframe = new JFrame(title);

        jframe.setPreferredSize(new Dimension(width, height));
        jframe.setMaximumSize(new Dimension(width, height));
        jframe.setMinimumSize(new Dimension(width, height));

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        //Makes window start in the middle of the screen.
        jframe.setLocationRelativeTo(null);
        jframe.add(game);
        jframe.setVisible(true);
        game.start();
    }
}
