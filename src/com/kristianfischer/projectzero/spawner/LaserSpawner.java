package com.kristianfischer.projectzero.spawner;

import com.kristianfischer.projectzero.component.CollisionGameComponent;
import com.kristianfischer.projectzero.component.MovementGameComponent;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.Laser;
import com.kristianfischer.projectzero.gameobject.attributes.Hitbox;

/**
 * Created by kristianhfischer on 7/27/15.
 */
public class LaserSpawner {

    public static final int LASER_WIDTH = 5;
    public static final int LASER_HEIGHT = 10;
    public static final int LASER_SPEED = 5;
    public static final int LASER_DAMAGE = 1;

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
