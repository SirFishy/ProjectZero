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
    private int xVelocity;
    private int yVelocity;
    private boolean isActive;

    public GameButton(int x, int y, int width, int height) {
        mButtonState = State.NONE;
        mButtonFrame = new Rectangle(x, y, width, height);
        xTextOffset = 0;
        yTextOffset = 0;
        xVelocity = 0;
        yVelocity = 0;
        isActive = false;
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

                break;
            case PRESSED:
                drawButtonRectangle(g, Color.WHITE);
                fillButtonRectangle(g, Color.WHITE);
                drawButtonText(g, Color.GREEN);

                break;
            case RELEASED:
                drawButtonRectangle(g, Color.BLACK);
                fillButtonRectangle(g, Color.WHITE);
                drawButtonText(g, Color.RED);

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

    public void tick( Point point ) {

        if( point == null )
            return;

        if( mButtonFrame.contains( point ) && mButtonState.equals( State.NONE) ) {
            mButtonState = State.HOVER;
            if( mButtonListener != null ) mButtonListener.onButtonHovered();
        } else if( !mButtonFrame.contains(point) ) {
            mButtonState = State.NONE;
        }

        //System.out.println("Made it to here: " + xVelocity);

        mButtonFrame.setLocation(
                (int) mButtonFrame.getX() + xVelocity,
                (int) mButtonFrame.getY() + yVelocity);


    }

    public void press( Point point ) {
        if( mButtonFrame.contains( point ) ) {
            mButtonState = State.PRESSED;
            if( mButtonListener != null ) mButtonListener.onButtonPressed();
        }

    }

    public void release( Point point ) {
        if( mButtonFrame.contains( point ) &&
                mButtonState.equals(State.PRESSED) ) {
            mButtonState = State.RELEASED;
            if( mButtonListener != null ) mButtonListener.onButtonReleased();
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

    public void setxVelocity( int xVelocity ) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity( int yVelocity ) {
        this.yVelocity = yVelocity;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setxPosition( int xPosition ) {
        mButtonFrame.setLocation(xPosition, (int) mButtonFrame.getY());
    }

    public void setyPosition( int yPosition ) {
        mButtonFrame.setLocation( (int) mButtonFrame.getX(), yPosition);
    }


    public int getxPosition() {
        return (int) mButtonFrame.getX();
    }

    public int getyPosition() {
        return (int) mButtonFrame.getY();
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
