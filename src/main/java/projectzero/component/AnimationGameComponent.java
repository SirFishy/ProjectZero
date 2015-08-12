package main.java.projectzero.component;

import main.java.projectzero.animation.Animation;
import main.java.projectzero.gameobject.GameObject;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kristianhfischer on 8/12/15.
 */
public class AnimationGameComponent extends GameComponent {

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
        mCurrentAnimation.tick();
    }

    @Override
    public void render(Graphics g) throws IllegalStateException {
        if( mCurrentAnimation != null ) {
            g.drawImage(mCurrentAnimation.getSprite(), mGameObject.getxPosition(), mGameObject.getyPosition(), null);
        }
    }

    public void addAnimation( String animationName, Animation animation ) {
        if( mGameObjectAnimations == null ) {
            System.out.println("Map is null");
        }
        mGameObjectAnimations.put(animationName, animation);
    }

    public void setAnimation(String animationName) {
        mCurrentAnimation = mGameObjectAnimations.get( animationName );
        mCurrentAnimation.start();
    }

    public void stopAnimating() {
        mCurrentAnimation.stop();
        mCurrentAnimation.reset();
    }
}
