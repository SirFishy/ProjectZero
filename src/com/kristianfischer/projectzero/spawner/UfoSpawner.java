package com.kristianfischer.projectzero.spawner;

import com.kristianfischer.projectzero.component.CollisionGameComponent;
import com.kristianfischer.projectzero.component.MovementGameComponent;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.Ufo;
import com.kristianfischer.projectzero.gameobject.attributes.Hitbox;

/**
 * Created by kristianhfischer on 7/31/15.
 */
public class UfoSpawner {

    public static final int UFO_HEIGHT = 10;
    public static final int UFO_WIDTH = 20;
    public static final int UFO_SPEED = 3;
    public static final int MAX_NUMBER_OF_TURNS = 4;
    public static final int MIN_NUMBER_OF_TURNS = 1;

    private UfoSpawner() {}
    public static Ufo SpawnUfo( int xPosition, int yPosition, int score ) {
        Ufo ufo = new Ufo.Builder()
                .xPosition(xPosition)
                .yPosition(yPosition)
                .height(UFO_HEIGHT)
                .width(UFO_WIDTH)
                .speed(UFO_SPEED)
                .gameId(GameId.UFO)
                .score(score)
                .collisionComponent( new CollisionGameComponent() )
                .movementComponent( new MovementGameComponent() )
                .isActive(true)
                .build();
        ufo.getCollisionComponent().setHitbox(new Hitbox.Builder(ufo)
                .rectangle(0, 0, UFO_WIDTH, UFO_HEIGHT)
                .build());
        return ufo;
    }
}
