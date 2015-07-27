package com.kristianfischer.projectzero.level;

import com.kristianfischer.projectzero.component.CollisionGameComponent;
import com.kristianfischer.projectzero.component.MovementGameComponent;
import com.kristianfischer.projectzero.game.Game;
import com.kristianfischer.projectzero.game.GameId;
import com.kristianfischer.projectzero.game.GameWindow;
import com.kristianfischer.projectzero.gameobject.Bunker;
import com.kristianfischer.projectzero.gameobject.GameObject;
import com.kristianfischer.projectzero.gameobject.Player;
import com.kristianfischer.projectzero.gameobject.SpaceGrunt;
import com.kristianfischer.projectzero.gameobject.attributes.Hitbox;
import com.kristianfischer.projectzero.handler.GameHandler;
import com.kristianfischer.projectzero.spawner.BunkerSpawner;

/**
 * Created by kristianhfischer on 7/25/15.
 */
public class LevelOne {

    private static final int X_OFFSET = 169;
    private static final int Y_OFFSET = 10;
    private static final int NUMBER_OF_ROWS = 3;
    private static final int ENEMIES_PER_ROW = 11;

    private GameHandler mGameHandler;

    public LevelOne( GameHandler handler ) {
        mGameHandler = handler;
    }

    public void build() {
        GameObject player = new Player.Builder()
                .xPosition(Game.WIDTH / 2 - 16)
                .yPosition(Game.HEIGHT - 42)
                .width(32)
                .height(32)
                .gameId(GameId.PLAYER)
                .speed(5)
                .isActive(true)
                .movementComponent(new MovementGameComponent())
                .collisionComponent(new CollisionGameComponent())
                .build();
        player.getCollisionComponent().setHitbox(new Hitbox.Builder(player)
                .rectangle(0, 0, player.getWidth(), player.getHeight())
                .build());
        mGameHandler.addGameObject(player);
        GameObject bunker = BunkerSpawner.SpawnBunker(X_OFFSET,
                Game.HEIGHT - 42 - BunkerSpawner.BUNKER_HEIGHT - 10);
        mGameHandler.addGameObject(bunker);
        for( int row = 0; row < NUMBER_OF_ROWS; row++) {
            generateEnemyRow(X_OFFSET, Y_OFFSET + 42 * row, ENEMIES_PER_ROW);
        }
    }

    private void generateEnemyRow(int startingX, int startingY, int numberOfEnemies) {
        for( int enemyCount = 0; enemyCount < numberOfEnemies; enemyCount ++) {
            GameObject grunt = new SpaceGrunt.Builder()
                    .xPosition(startingX + 42 * enemyCount )
                    .yPosition(startingY)
                    .width(32)
                    .height(32)
                    .gameId(GameId.ENEMY)
                    .speed(2)
                    .isActive(true)
                    .movementComponent(new MovementGameComponent())
                    .collisionComponent(new CollisionGameComponent())
                    .build();
            grunt.getCollisionComponent().setHitbox(new Hitbox.Builder(grunt)
                    .rectangle(0, 0, grunt.getWidth(), grunt.getHeight())
                    .build());
            mGameHandler.addGameObject(grunt);
        }

    }
}
