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
    private Button LevelSelect;

    @FXML
    private Button HowToPlay;

    @FXML
    private Button Exit;

    private DungeonScreen DungeonScreen;

    private LevelSelectScreen levelSelectScreen;

    private HowToPlayScreen howToPlayScreen;

    @FXML
    public void handlePlay(ActionEvent event) throws IOException {
        DungeonScreen.start();
    }

    @FXML
    public void HandleLevelSelect(ActionEvent event) {
        levelSelectScreen.start();
    }

    @FXML
    public void HandleHowToPlay(ActionEvent event) {
        howToPlayScreen.start();
    }

    @FXML
    public void HandleExit(ActionEvent event) {
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
