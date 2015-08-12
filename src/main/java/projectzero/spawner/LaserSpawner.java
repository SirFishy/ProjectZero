package main.java.projectzero.spawner;

import main.java.projectzero.component.CollisionGameComponent;
import main.java.projectzero.component.MovementGameComponent;
import main.java.projectzero.game.GameId;
import main.java.projectzero.gameobject.Laser;
import main.java.projectzero.gameobject.attributes.Hitbox;

/**
 * Created by kristianhfischer on 7/27/15.
 */
public class LaserSpawner {

    public static final int LASER_WIDTH = 5;
    public static final int LASER_HEIGHT = 10;
    public static final int LASER_SPEED = 5;
    public static final int LASER_DAMAGE = 1;

    private LaserSpawner() {}

    public static Laser SpawnLaser(int xPosition, int yPosition, GameId gameId) {
        Laser laser = new Laser.Builder()
                .xPosition(xPosition)
                .yPosition(yPosition)
                .width(LASER_WIDTH)
                .height(LASER_HEIGHT)
                .gameId(gameId)
                .speed(LASER_SPEED)
                .damage(LASER_DAMAGE)
                .isActive(true)
                .collisionComponent(new CollisionGameComponent())
                .movementComponent(new MovementGameComponent())
                .build();
        laser.getCollisionComponent().setHitbox(new Hitbox.Builder(laser)
                .rectangle(0, 0, laser.getWidth(), laser.getHeight())
                .build());
        return laser;
    }
}
