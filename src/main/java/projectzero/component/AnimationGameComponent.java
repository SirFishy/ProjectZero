package main.java.projectzero.component;

import main.java.projectzero.animation.Animation;
import main.java.projectzero.gameobject.GameObject;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kristianhfischer on 8/12/15.
 * A IGameComponent that can be added to a GameObject to call all of the required methods for animation
 */
public class AnimationGameComponent implements IGameComponent {

    private Map<String, Animation> mGameObjectAnimations;
    private Animation mCurrentAnimation;
    private GameObject mGameObject;

    @Override
    public void initialize(GameObject gameObject) {
        mGameObjectAnimations = new HashMap<>();
        mGameObject = gameObject;
    }

    @Override
    public void tick() throws IllegalStateException {
        if( mGameObject == null ) throw new IllegalStateException("GameObject for AnimationGameComponent is null.");
        if( mCurrentAnimation == null )
            throw new IllegalStateException("Current Animation for AnimationGameComponent is null");
        mCurrentAnimation.tick();
    }

    @Override
    public void render(Graphics g) throws IllegalStateException {
        if( mCurrentAnimation == null )
            throw new IllegalStateException("Current Animation for AnimationGameComponent is null");
        g.drawImage(mCurrentAnimation.getSprite(), mGameObject.getxPosition(), mGameObject.getyPosition(), null);
    }

    /**
     * addAnimation Adds an animation to the AnimationComponent
     * @param animationName - the String name of the animation so that it can be accessed later
     * @param animation - the Animation object associated with the name
     */
    public void addAnimation( String animationName, Animation animation ) {
        mGameObjectAnimations.put(animationName, animation);
    }

    /**
     * setAnimation sets the current animation being played for the GameObject
     * @param animationName - The name of the animation that should be played
     */
    public void setAnimation(String animationName) throws IllegalStateException {
        try {
            mCurrentAnimation = mGameObjectAnimations.get( animationName );
            mCurrentAnimation.start();
        } catch( NullPointerException e) {
            throw new IllegalStateException("Animation " + animationName + " was never added to AnimationComponent");
        }
    }

    /**
     * stopAnimating will stop the GameObject's animations
     */
    public void stopAnimating() {
        mCurrentAnimation.stop();
        mCurrentAnimation.reset();
    }
}
