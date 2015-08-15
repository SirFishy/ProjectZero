package main.java.projectzero.level;

import main.java.projectzero.game.Game;
import main.java.projectzero.gameobject.GameObject;
import main.java.projectzero.gameobject.Player;
import main.java.projectzero.gameobject.SpaceGrunt;
import main.java.projectzero.handler.GameHandler;
import main.java.projectzero.spawner.BunkerSpawner;
import main.java.projectzero.spawner.GruntSpawner;
import main.java.projectzero.spawner.PlayerSpawner;

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
            generateEnemyRow(X_OFFSET, Y_OFFSET + 26 * row, ENEMIES_PER_ROW, score,
                    GruntSpawner.HIGH_GRUNT_SPRITE_FILENAME);
        }
    }

    private void generateEnemyRow(int startingX, int startingY, int numberOfEnemies, int score, String fileName) {
        for( int enemyCount = 0; enemyCount < numberOfEnemies; enemyCount ++) {
            SpaceGrunt grunt = GruntSpawner.SpawnGrunt(startingX +
                            (GruntSpawner.GRUNT_WIDTH + GRUNT_SPACING) * enemyCount,
                    startingY, score, fileName);
            mGameHandler.addGameObject(grunt);
        }

    }

}
