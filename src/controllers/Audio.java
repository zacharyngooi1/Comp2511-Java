
package controllers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Audio {
    public static final Media boulderPush = createSound("boulder_push.wav");
    public static final Media buttonPress = createSound("button_press.wav");
    public static final Media doorUnlock = createSound("door_unlock.wav");
    public static final Media enemyDeath = createSound("enemy_death.wav");
    public static final Media invincibilityPickup = createSound("invincibility_drink.wav");
    public static final Media keyPickup = createSound("keys_pickup.wav");
    public static final Media bgm = createSound("kings-of-tara-by-kevin-macleod-from-filmmusic-io.mp3");
    public static final Media lose = createSound("lose.wav");
    public static final Media playerMove = createSound("player_move.wav");
    public static final Media switchOff = createSound("switch_off.wav");
    public static final Media switchOn = createSound("switch_on.wav");
    public static final Media swordPickup = createSound("sword_pickup.wav");
    public static final Media teleport = createSound("teleport.wav");
    public static final Media treasurePickup = createSound("treasure_pickup.wav");
    public static final Media win = createSound("win.wav");

    public static void playSound(Media sound, Boolean infinite) {
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        if (infinite) {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }

        mediaPlayer.setOnEndOfMedia(new Thread() {
            @Override
            public void run() {
                mediaPlayer.dispose();
            }
        });

        mediaPlayer.play();
    }

    public static void playSound(Media sound) {
        playSound(sound, false);
    }

    private static Media createSound(String fileName) {
        return new Media(new File("src/resources/audio/" + fileName).toURI().toString());
    }
}
