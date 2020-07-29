package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;


import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 *
 * @author Robert Clifton-Everest
 */
public class LevelSelectController {
    @FXML
    private Button Level1;

    @FXML
    private Button Level2;

    @FXML
    private Button Level3;

    @FXML
    private Button Back;
    
    private DungeonScreen dungeonScreen;

    private StartScreen startScreen;

    private ResetScreen resetScreen;

    @FXML
	public void HandleLevel1(ActionEvent event) throws IOException {
        System.out.println("level 1 pressed!");
        dungeonScreen.SetStage(1);
        dungeonScreen.getController().setResetScreen(resetScreen);
        dungeonScreen.start();
    }

    @FXML
	public void HandleLevel2(ActionEvent event) throws IOException {
        System.out.println("Level 2 pressed!");
        dungeonScreen.SetStage(2);
        dungeonScreen.getController().setResetScreen(resetScreen);
        dungeonScreen.start();
    }

    @FXML
	public void HandleLevel3(ActionEvent event) throws IOException {
        System.out.println("Level 3 pressed!");
        dungeonScreen.SetStage(3);
        dungeonScreen.getController().setResetScreen(resetScreen);
        dungeonScreen.start();
    }

    @FXML
	public void HandleBack(ActionEvent event) {
        System.out.println("Play pressed!");
        startScreen.start();
    }

    public void setDungeonScreen(DungeonScreen dungeonscreen) {
        this.dungeonScreen = dungeonscreen;
    }

    public void setStartScreen(StartScreen screen) {
        this.startScreen = screen;
    }

    public void setResetScreen(ResetScreen screen) {
        this.resetScreen = screen;
    } 


}