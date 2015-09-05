package main.java.projectzero.artificalbehavior;

import main.java.projectzero.command.ICommand;
import main.java.projectzero.command.MoveDownCommand;
import main.java.projectzero.game.Game;
import main.java.projectzero.game.GameId;
import main.java.projectzero.gameobject.GameObject;

/**
 * Created by kristianhfischer on 7/20/15.
 * Implements behavior of enemy projectiles
 */
public class EnemyProjectileBehavior extends Behavior{

    private ICommand moveDown;

    public EnemyProjectileBehavior(GameObject gameObject) throws IllegalStateException {
        super(gameObject);
        if( !gameObject.getGameId().equals(GameId.ENEMY_PROJECTILE) )
            throw new IllegalStateException("GameObject is not an enemy projectile");
        moveDown = new MoveDownCommand();
        moveDown.execute(this.gameObject);
    }

    public void tick() {

        boolean reachedLevelBoundary = gameObject.getyPosition() <= 0 ||
                gameObject.getyPosition() >= (Game.HEIGHT - gameObject.getHeight());
        if(reachedLevelBoundary ) {
            gameObject.setIsDestroyed(true);
            //System.out.println("Destroying Enemy Projectile");
        }
    }
}
