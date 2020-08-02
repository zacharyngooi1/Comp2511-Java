package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

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

    private AllLevelCompleteScreen allLevelComplete;

    @FXML
    public void handleNextLevel(ActionEvent event) throws IOException {
        if (dungeonScreen.getInt() == 3) {
            allLevelComplete.start();
            return;
        }

        dungeonScreen.SetStage(dungeonScreen.getInt() + 1);
        dungeonScreen.getController().setResetScreen(resetScreen);
        dungeonScreen.getController().setStartScreen(startScreen);
        dungeonScreen.getController().setWinScreen(winScreen);
        dungeonScreen.start();
    }

    @FXML
    public void handleMainMenu(ActionEvent event) {
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

    public void setAllLevelComplete(AllLevelCompleteScreen endingscreen) {
        this.allLevelComplete = endingscreen;
    }
}
