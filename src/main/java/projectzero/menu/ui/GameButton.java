package main.java.projectzero.menu.ui;

import java.awt.*;

/**
 * Created by kristianhfischer on 9/5/15.
 */
public class GameButton {

    private Rectangle mButtonFrame;
    private String mButtonText;
    private IButtonListener mButtonListener;
    private State mButtonState;

    private int xTextOffset;
    private int yTextOffset;

    public GameButton(int x, int y, int width, int height) {
        mButtonState = State.NONE;
        mButtonFrame = new Rectangle(x, y, width, height);
        xTextOffset = 0;
        yTextOffset = 0;
    }

    private enum State {
        NONE,
        HOVER,
        PRESSED,
        RELEASED;
    }

    public interface IButtonListener {
        void onButtonReleased();
        void onButtonPressed();
        void onButtonHovered();
    }

    public void render(Graphics g) {
        Font font = new Font("Dialog", Font.PLAIN, 14);
        g.setFont(font);
        g.setColor(Color.WHITE);

        switch (mButtonState) {
            case NONE:
                drawButtonRectangle(g, Color.WHITE);
                drawButtonText(g, Color.WHITE);
                break;
            case HOVER:
                drawButtonRectangle(g, Color.WHITE);
                fillButtonRectangle(g, Color.WHITE);
                drawButtonText(g, Color.BLACK);
                if( mButtonListener != null ) mButtonListener.onButtonHovered();
                break;
            case PRESSED:
                drawButtonRectangle(g, Color.WHITE);
                fillButtonRectangle(g, Color.WHITE);
                drawButtonText(g, Color.GREEN);
                if( mButtonListener != null ) mButtonListener.onButtonPressed();
                break;
            case RELEASED:
                drawButtonRectangle(g, Color.BLACK);
                fillButtonRectangle(g, Color.WHITE);
                drawButtonText(g, Color.RED);
                if( mButtonListener != null ) mButtonListener.onButtonReleased();
                break;
            default:
                break;
        }


    }

    public void setButtonListener( IButtonListener listener ) {
        mButtonListener = listener;
    }

    public void setButtonText( String text ) {
        mButtonText = text;
    }

    public void hover( Point point ) {

        if( point == null )
            return;

        if( mButtonFrame.contains( point ) && mButtonState.equals( State.NONE) ) {
            mButtonState = State.HOVER;
        } else if( !mButtonFrame.contains(point) ) {
            mButtonState = State.NONE;
        }

    }

    public void press( Point point ) {
        if( mButtonFrame.contains( point ) ) {
            mButtonState = State.PRESSED;
        }

    }

    public void release( Point point ) {
        if( mButtonFrame.contains( point ) &&
                mButtonState.equals(State.PRESSED) ) {
            mButtonState = State.RELEASED;
        }
    }

    public void reset() {
        mButtonState = State.NONE;
    }

    public void setxTextOffset(int xTextOffset) {
        this.xTextOffset = xTextOffset;
    }

    public void setyTextOffset(int yTextOffset) {
        this.yTextOffset = yTextOffset;
    }

    private void drawButtonText( Graphics g, Color color ) {
        Color tempColor = g.getColor();
        g.setColor(color);
        g.drawString(mButtonText,
                (int) mButtonFrame.getX() + xTextOffset,
                (int) mButtonFrame.getY() + yTextOffset );
        g.setColor(tempColor);
    }

    private void fillButtonRectangle( Graphics g, Color color ) {
        Color tempColor = g.getColor();
        g.setColor(color);
        g.fillRect((int) mButtonFrame.getX(),
                (int) mButtonFrame.getY(),
                (int) mButtonFrame.getWidth(),
                (int) mButtonFrame.getHeight());
        g.setColor(tempColor);
    }

    private void drawButtonRectangle( Graphics g, Color color ) {
        Color tempColor = g.getColor();
        g.setColor(color);
        g.drawRect((int) mButtonFrame.getX(),
                (int) mButtonFrame.getY(),
                (int) mButtonFrame.getWidth(),
                (int) mButtonFrame.getHeight());
        g.setColor(tempColor);
    }

}
