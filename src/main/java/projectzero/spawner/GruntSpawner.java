package main.java.projectzero.spawner;

import main.java.projectzero.animation.Animation;
import main.java.projectzero.animation.Sprite;
import main.java.projectzero.component.AnimationGameComponent;
import main.java.projectzero.component.CollisionGameComponent;
import main.java.projectzero.component.MovementGameComponent;
import main.java.projectzero.game.GameId;
import main.java.projectzero.gameobject.SpaceGrunt;
import main.java.projectzero.gameobject.attributes.Hitbox;

import java.awt.image.BufferedImage;
import java.util.ResourceBundle;

/**
 * Created by kristianhfischer on 7/27/15.
 */
public class GruntSpawner {

    public static final int GRUNT_HEIGHT = 16;
    public static final int GRUNT_WIDTH = 16;
    public static final int GRUNT_SPEED = 2;
    public static final int TOP_ROW_GRUNT_SCORE = 100;
    public static final int MIDDLE_ROW_GRUNT_SCORE = 50;
    public static final int BOTTOM_ROW_GRUNT_SCORE = 20;

    public static final String GRUNT_MOVE_ANIMATION = "Moving Grunt";
    public static final int ANIMATION_DELAY = 10;

    private GruntSpawner() {}

    public static SpaceGrunt SpawnGrunt( int xPosition, int yPosition, int score ) {
        SpaceGrunt grunt = new SpaceGrunt.Builder()
                .xPosition( xPosition )
                .yPosition( yPosition )
                .width(GRUNT_WIDTH)
                .height(GRUNT_HEIGHT)
                .gameId(GameId.ENEMY)
                .speed(GRUNT_SPEED)
                .score(score)
                .isActive(true)
                .movementComponent(new MovementGameComponent())
                .collisionComponent(new CollisionGameComponent())
                .animationComponent(new AnimationGameComponent())
                .build();
        grunt.getCollisionComponent().setHitbox(new Hitbox.Builder(grunt)
                .rectangle(0, 0, grunt.getWidth(), grunt.getHeight())
                .build());
        String highGruntFile = "/HighGrunt.png";
        BufferedImage[] floating = {
                Sprite.getSprite(0, 0, highGruntFile),
                Sprite.getSprite(1, 0, highGruntFile)
        };
        Animation moveAnimation = new Animation(floating, ANIMATION_DELAY);
        grunt.getAnimationComponent().addAnimation(GRUNT_MOVE_ANIMATION, moveAnimation);
        grunt.getAnimationComponent().setAnimation(GRUNT_MOVE_ANIMATION);

        return grunt;
    }
}
