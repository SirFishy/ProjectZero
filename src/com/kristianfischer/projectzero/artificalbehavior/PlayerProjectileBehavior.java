package com.kristianfischer.projectzero.artificalbehavior;

import com.kristianfischer.projectzero.command.Command;
import com.kristianfischer.projectzero.command.MoveUpCommand;
import com.kristianfischer.projectzero.game.Game;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.handler.DynamicGameObjectHandler;

/**
 * Created by kristianhfischer on 7/20/15.
 */
public class PlayerProjectileBehavior extends Behavior{

    private Command moveUp;


    public PlayerProjectileBehavior(GameObject gameObject) throws IllegalStateException {
        super(gameObject);
        if( !gameObject.getGameId().equals(GameId.PLAYER_PROJECTILE) )
            throw new IllegalStateException("GameObject is not a player projectile");
        moveUp = new MoveUpCommand();
        moveUp.execute(this.gameObject);

    }

    public void update() {
        if( gameObject.isDestroyed() ) {
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(gameObject);

        }

        boolean reachedLevelBoundary = gameObject.getyPosition() <= 0 ||
                gameObject.getyPosition() >= (Game.HEIGHT - gameObject.getHeight());
        if(reachedLevelBoundary ) {
            System.out.println("Destorying Player Projectile");
            gameObject.setIsDestroyed(true);
            DynamicGameObjectHandler.getInstance().addDestroyedGameObject(gameObject);
        }
    }
}
