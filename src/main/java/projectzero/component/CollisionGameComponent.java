package main.java.projectzero.component;

import main.java.projectzero.gameobject.GameObject;
import main.java.projectzero.gameobject.attributes.Hitbox;

import java.awt.*;

/**
 * Created by kristianhfischer on 7/16/15.
 * A IGameComponent that can be added to a GameObject to call all of the required methods for collision
 */
public class CollisionGameComponent implements IGameComponent {

    private GameObject mGameObject;
    private Hitbox mHitbox;

    public CollisionGameComponent() {
    }

    @Override
    public void initialize(GameObject gameObject) {
        mGameObject = gameObject;
    }

    @Override
    public void tick() throws IllegalStateException {
        if( mHitbox != null ) mHitbox.udpate();
        else throw new IllegalStateException("GameObject CollisionComponent hitbox was not set");
    }

    @Override
    public void render(Graphics g) throws IllegalStateException {
        try {
            mHitbox.render(g);
        } catch(NullPointerException e) {
            throw new IllegalStateException("GameObject CollisionComponent hitbox was not set");
        }
    }

    /**
     * setHitbox will set the GameObject's Hitbox. A Hitobx is required for collision detection to occur
     * @param hitbox
     */
    public void setHitbox( Hitbox hitbox ) {
        mHitbox = hitbox;
    }

    /**
     * getHitbox
     * @return GameObject's Hitbox
     */
    public Hitbox getHitbox( ) {
        return mHitbox;
    }
}