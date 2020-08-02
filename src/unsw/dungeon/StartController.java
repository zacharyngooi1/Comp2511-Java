package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;


import java.io.File;

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
        System.out.println("Play pressed!");
        DungeonScreen.start();
    }

    @FXML
	public void HandleLevelSelect(ActionEvent event) {
        System.out.println("Level Select pressed!");
        levelSelectScreen.start();
    }

    @FXML
	public void HandleHowToPlay(ActionEvent event) {
        System.out.println("how to play pressed!");
        howToPlayScreen.start();
    }

    @FXML
	public void HandleExit(ActionEvent event) {
        System.out.println("exit pressed!");
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