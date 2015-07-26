package com.kristianfischer.projectzero.artificalbehavior;

import com.kristianfischer.projectzero.command.Command;
import com.kristianfischer.projectzero.command.MoveDownCommand;
import com.kristianfischer.projectzero.game.Game;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;

/**
 * Created by kristianhfischer on 7/20/15.
 */
public class EnemyProjectileBehavior extends Behavior{

    private Command moveDown;

    public EnemyProjectileBehavior(GameObject gameObject) throws IllegalStateException {
        super(gameObject);
        if( !gameObject.getGameId().equals(GameId.ENEMY_PROJECTILE) )
            throw new IllegalStateException("GameObject is not an enemy projectile");
        moveDown = new MoveDownCommand();
        moveDown.execute(this.gameObject);
    }

    public void update() {

        if( gameObject.isDestroyed() ) {
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(gameObject);
            return;
        }

        boolean reachedLevelBoundary = gameObject.getyPosition() <= 0 ||
                gameObject.getyPosition() >= (Game.HEIGHT - gameObject.getHeight());
        if(reachedLevelBoundary ) {
            gameObject.setIsDestroyed(true);
            System.out.println("Destroying Enemy Projectile");
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(gameObject);
        }
    }
}
