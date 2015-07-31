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
import com.kristianfischer.projectzero.spawner.GruntSpawner;
import com.kristianfischer.projectzero.spawner.PlayerSpawner;

/**
 * Created by kristianhfischer on 7/25/15.
 */
public class LevelOne {


    private static final int NUMBER_OF_ROWS = 4;
    private static final int ENEMIES_PER_ROW = 8;
    private static final int NUMBER_OF_BUNKERS = 3;
    private static final int GRUNT_SPACING = 20;
    private static final int BUNKER_SPACING = BunkerSpawner.BUNKER_WIDTH * 2;
    private static final int X_OFFSET = (Game.WIDTH - 60) - ( BUNKER_SPACING * NUMBER_OF_BUNKERS )  ;
    private static final int Y_OFFSET = 30;

    private GameHandler mGameHandler;

    public LevelOne( GameHandler handler ) {
        mGameHandler = handler;
    }

    public void build() {
        Player player = PlayerSpawner.SpawnPlayer(Game.WIDTH / 2 - PlayerSpawner.PLAYER_WIDTH,
                Game.HEIGHT - PlayerSpawner.PLAYER_HEIGHT - 10);
        mGameHandler.addGameObject(player);
        for( int bunk = 0; bunk < NUMBER_OF_BUNKERS; bunk ++) {
            GameObject bunker = BunkerSpawner.SpawnBunker(X_OFFSET + bunk * BUNKER_SPACING,
                    Game.HEIGHT - 42 - BunkerSpawner.BUNKER_HEIGHT - 10);
            mGameHandler.addGameObject(bunker);
        }

        for( int row = 0; row < NUMBER_OF_ROWS; row++) {
            int score;
            if( row == 0 )
                score = GruntSpawner.TOP_ROW_GRUNT_SCORE;
            else if( row == NUMBER_OF_ROWS - 1 )
                score = GruntSpawner.BOTTOM_ROW_GRUNT_SCORE;
            else
                score = GruntSpawner.MIDDLE_ROW_GRUNT_SCORE;
            generateEnemyRow(X_OFFSET, Y_OFFSET + 26 * row, ENEMIES_PER_ROW, score);
        }
    }

    private void generateEnemyRow(int startingX, int startingY, int numberOfEnemies, int score) {
        for( int enemyCount = 0; enemyCount < numberOfEnemies; enemyCount ++) {
            SpaceGrunt grunt = GruntSpawner.SpawnGrunt(startingX +
                            (GruntSpawner.GRUNT_WIDTH + GRUNT_SPACING) * enemyCount,
                    startingY, score);
            mGameHandler.addGameObject(grunt);
        }

    }

}
