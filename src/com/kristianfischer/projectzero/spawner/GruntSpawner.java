package com.kristianfischer.projectzero.spawner;

import com.kristianfischer.projectzero.component.CollisionGameComponent;
import com.kristianfischer.projectzero.component.MovementGameComponent;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.SpaceGrunt;
import com.kristianfischer.projectzero.gameobject.attributes.Hitbox;

/**
 * Created by kristianhfischer on 7/27/15.
 */
public class GruntSpawner {

    public static final int GRUNT_HEIGHT = 16;
    public static final int GRUNT_WIDTH = 16;
    public static final int GRUNT_SPEED = 2;

    public static SpaceGrunt SpawnGrunt( int xPosition, int yPosition ) {
        SpaceGrunt grunt = new SpaceGrunt.Builder()
                .xPosition( xPosition )
                .yPosition( yPosition )
                .width(GRUNT_WIDTH)
                .height(GRUNT_HEIGHT)
                .gameId(GameId.ENEMY)
                .speed(GRUNT_SPEED)
                .isActive(true)
                .movementComponent(new MovementGameComponent())
                .collisionComponent(new CollisionGameComponent())
                .build();
        grunt.getCollisionComponent().setHitbox(new Hitbox.Builder(grunt)
                .rectangle(0, 0, grunt.getWidth(), grunt.getHeight())
                .build());
        return grunt;
    }
}
