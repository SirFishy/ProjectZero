package main.java.projectzero.artificalbehavior;

import main.java.projectzero.command.Command;
import main.java.projectzero.command.MoveUpCommand;
import main.java.projectzero.game.Game;
import main.java.projectzero.game.GameId;
import main.java.projectzero.gameobject.GameObject;

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

    public void tick() {
        boolean reachedLevelBoundary = gameObject.getyPosition() <= 0 ||
                gameObject.getyPosition() >= (Game.HEIGHT - gameObject.getHeight());
        if(reachedLevelBoundary ) {
            //System.out.println("Destorying Player Projectile");
            gameObject.setIsDestroyed(true);
        }
    }
}
