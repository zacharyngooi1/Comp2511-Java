package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;


import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 *
 * @author Robert Clifton-Everest
 */
public class WinController {
    @FXML
    private Button nextLevel;

    @FXML
    private Button mainMenu;
    
    private DungeonScreen dungeonScreen;

    private StartScreen startScreen;

    private ResetScreen resetScreen;

    private WinScreen winScreen;

    @FXML
	public void HandleNextLevel(ActionEvent event) throws IOException {
        System.out.println("next level pressed!");
        if (dungeonScreen.getInt() == 3) {
            // Create a new scene for a all levek completed.
            System.out.println("All levels complete");
            startScreen.start();
            return;
        }
        dungeonScreen.SetStage(dungeonScreen.getInt() + 1);
        dungeonScreen.getController().setResetScreen(resetScreen);
        dungeonScreen.getController().setStartScreen(startScreen);
        dungeonScreen.getController().setWinScreen(winScreen);
        dungeonScreen.start();
    }

    @FXML
	public void HandleMainMenu(ActionEvent event) {
        System.out.println("Back pressed!");
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

    public void setWinScreen(WinScreen winscreen) {
        this.winScreen = winscreen;
    }

}