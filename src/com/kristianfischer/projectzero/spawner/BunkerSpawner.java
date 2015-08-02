package com.kristianfischer.projectzero.spawner;

import com.kristianfischer.projectzero.component.CollisionGameComponent;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.gameobject.Bunker;
import com.kristianfischer.projectzero.gameobject.BunkerSection;
import com.kristianfischer.projectzero.gameobject.attributes.Hitbox;

/**
 * Created by fischkh1 on 7/27/15.
 */
public class BunkerSpawner {

    public static final int BUNKER_SECTION_HEIGHT = 32;
    public static final int BUNKER_SECTION_WIDTH = 32;
    public static final int BUNKER_HEIGHT = BUNKER_SECTION_HEIGHT * 2;
    public static final int BUNKER_WIDTH = BUNKER_SECTION_WIDTH * 3;

    private BunkerSpawner() {}

    public static Bunker SpawnBunker(int xPosition, int yPosition) {
        Bunker bunker = new Bunker.Builder()
                .xPosition(xPosition)
                .yPosition(yPosition)
                .width(BUNKER_WIDTH)
                .height(BUNKER_HEIGHT)
                .gameId(GameId.BUNKER)
                .speed(0)
                .isActive(true)
                .bunkerSection(SpawnBunkerSection(xPosition, yPosition))
                .bunkerSection(SpawnBunkerSection(xPosition + BUNKER_SECTION_WIDTH, yPosition))
                .bunkerSection(SpawnBunkerSection(xPosition + 2 * BUNKER_SECTION_WIDTH, yPosition))
                .bunkerSection(SpawnBunkerSection(xPosition + 2 * BUNKER_SECTION_WIDTH, yPosition + BUNKER_SECTION_HEIGHT))
                .bunkerSection(SpawnBunkerSection(xPosition, yPosition + BUNKER_SECTION_HEIGHT))
                .build();
        return bunker;
    }

    private static BunkerSection SpawnBunkerSection(int xPosition, int yPosition) {
        BunkerSection section = new BunkerSection.Builder()
                .xPosition(xPosition)
                .yPosition(yPosition)
                .width(BUNKER_SECTION_WIDTH)
                .height(BUNKER_SECTION_HEIGHT)
                .collisionComponent(new CollisionGameComponent())
                .gameId(GameId.BUNKER)
                .speed(0)
                .isActive(true)
                .build();
        section.getCollisionComponent().setHitbox(
                new Hitbox.Builder(section)
                .rectangle(0, 0, BUNKER_SECTION_WIDTH, BUNKER_SECTION_HEIGHT)
                .build() );
        return section;
    }

}
