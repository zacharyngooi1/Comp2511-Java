package unsw.dungeon;

import javafx.scene.image.Image;

import java.io.File;

public class Images {
    private static final float spriteSize = 42;

    public static final Image playerIdle1Image = createImage("player_idle1.png");
    public static final Image playerIdle2Image = createImage("player_idle2.png");
    public static final Image playerAttackImage = createImage("player_attack.png");
    public static final Image wallImage = createImage("wall.png");
    public static final Image boulderImage = createImage("boulder.png");
    public static final Image enemyIdle1Image = createImage("enemy_idle1.png");
    public static final Image enemyIdle2Image = createImage("enemy_idle2.png");
    public static final Image enemyAttackImage = createImage("enemy_attack.png");
    public static final Image treasureImage = createImage("treasure.png");
    public static final Image exitImage = createImage("exit.png");
    public static final Image keyImage = createImage("key.png");
    public static final Image floorSwitchOnImage = createImage("floorswitch_down.png");
    public static final Image floorSwitchOffImage = createImage("floorswitch_up.png");
    public static final Image invincibilityImage = createImage("invincibility.png");
    public static final Image doorOpenImage = createImage("door_open.png");
    public static final Image doorCloseImage = createImage("door_closed.png");
    public static final Image portalImage = createImage("portal.png");
    public static final Image swordImage = createImage("sword.png");
    public static final Image dirtImage = createImage("dirt.png");

    private static Image createImage(String fileName) {
        return new Image((new File("src/resources/images/" + fileName)).toURI().toString(), spriteSize, spriteSize, true, true);
    }
}
