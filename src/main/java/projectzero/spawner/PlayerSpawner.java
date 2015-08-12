package main.java.projectzero.spawner;

import main.java.projectzero.component.CollisionGameComponent;
import main.java.projectzero.component.MovementGameComponent;
import main.java.projectzero.game.GameId;
import main.java.projectzero.gameobject.Player;
import main.java.projectzero.gameobject.attributes.Hitbox;

/**
 * Created by kristianhfischer on 7/27/15.
 */
public class PlayerSpawner {

    public static final int PLAYER_WIDTH = 16;
    public static final int PLAYER_HEIGHT = 16;
    public static final int PLAYER_SPEED = 5;

    private PlayerSpawner() {}

    public static Player SpawnPlayer(int xPosition, int yPosition) {
        Player player = new Player.Builder()
                .xPosition(xPosition)
                .yPosition(yPosition)
                .width(PLAYER_WIDTH)
                .height(PLAYER_HEIGHT)
                .gameId(GameId.PLAYER)
                .speed(PLAYER_SPEED)
                .isActive(true)
                .movementComponent(new MovementGameComponent())
                .collisionComponent(new CollisionGameComponent())
                .build();
        player.getCollisionComponent().setHitbox(new Hitbox.Builder(player)
                .rectangle(0, 0, player.getWidth(), player.getHeight())
                .build());
        return player;
    }
}
