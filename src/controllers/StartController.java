package controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * A JavaFX controller for the dungeon.
 *
 * @author Robert Clifton-Everest
 */
public class StartController{
    @FXML
    private Button play;

    @FXML
    private Button levelSelect;

    @FXML
    private Button howToPlay;

    @FXML
    private Button exit;

    private DungeonScreen DungeonScreen;

    private LevelSelectScreen levelSelectScreen;

    private HowToPlayScreen howToPlayScreen;

    @FXML
    public void handlePlay(ActionEvent event) throws IOException {
        DungeonScreen.start();
        Audio.playSound(Audio.buttonPress);
    }

    @FXML
    public void handleLevelSelect(ActionEvent event) {
        levelSelectScreen.start();
        Audio.playSound(Audio.buttonPress);
    }

    @FXML
    public void handleHowToPlay(ActionEvent event) {
        howToPlayScreen.start();
        Audio.playSound(Audio.buttonPress);
    }

    @FXML
    public void handleExit(ActionEvent event) {
        Platform.exit();
    }

    public void setDungeonScreen(DungeonScreen dungeonscreen) {
        this.DungeonScreen = dungeonscreen;
    }

    public void setLevelSelect(LevelSelectScreen levelSelectScreen) {
        this.levelSelectScreen = levelSelectScreen;
    }

    public void setHowToPlayScreen(HowToPlayScreen howToPlay) {
        this.howToPlayScreen = howToPlay;
    }
}
