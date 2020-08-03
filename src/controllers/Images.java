package controllers;

import javafx.scene.image.Image;

import java.io.File;

public class Images {
    private static final float spriteSize = 42;

    public static final Image playerIdle1 = createImage("player_idle1.png");
    public static final Image playerIdle2 = createImage("player_idle2.png");
    public static final Image playerAttack = createImage("player_attack.png");
    public static final Image playerPickup = createImage("player_pickup.png");
    public static final Image playerTeleport = createImage("player_teleport.png");
    public static final Image wall = createImage("wall.png");
    public static final Image boulder = createImage("boulder.png");
    public static final Image enemyIdle1 = createImage("enemy_idle1.png");
    public static final Image enemyIdle2 = createImage("enemy_idle2.png");
    public static final Image enemyAttack = createImage("enemy_attack.png");
    public static final Image enemyTeleport = createImage("enemy_teleport.png");
    public static final Image treasure = createImage("treasure.png");
    public static final Image exit = createImage("exit.png");
    public static final Image key = createImage("key.png");
    public static final Image floorSwitchOn = createImage("floorswitch_down.png");
    public static final Image floorSwitchOff = createImage("floorswitch_up.png");
    public static final Image invincibility = createImage("invincibility.png");
    public static final Image doorOpen = createImage("door_open.png");
    public static final Image doorClose = createImage("door_closed.png");
    public static final Image portal = createImage("portal.png");
    public static final Image sword = createImage("sword.png");
    public static final Image dirt = createImage("dirt.png");

    private static Image createImage(String fileName) {
        return new Image((new File("src/resources/images/" + fileName)).toURI().toString(), spriteSize, spriteSize, true, true);
    }
}
