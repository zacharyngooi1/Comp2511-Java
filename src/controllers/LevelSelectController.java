package controllers;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

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

    private WinScreen winScreen;

    @FXML
    public void HandleLevel1(ActionEvent event) throws IOException {
        dungeonScreen.SetStage(1);
        dungeonScreen.getController().setResetScreen(resetScreen);
        dungeonScreen.getController().setStartScreen(startScreen);
        dungeonScreen.getController().setWinScreen(winScreen);
        resetScreen.getController().setDungeonScreen(dungeonScreen);
        dungeonScreen.start();
    }

    @FXML
    public void HandleLevel2(ActionEvent event) throws IOException {
        dungeonScreen.SetStage(2);
        dungeonScreen.getController().setResetScreen(resetScreen);
        dungeonScreen.getController().setStartScreen(startScreen);
        dungeonScreen.getController().setWinScreen(winScreen);
        resetScreen.getController().setDungeonScreen(dungeonScreen);
        dungeonScreen.start();
    }

    @FXML
    public void HandleLevel3(ActionEvent event) throws IOException {
        dungeonScreen.SetStage(3);
        dungeonScreen.getController().setResetScreen(resetScreen);
        dungeonScreen.getController().setStartScreen(startScreen);
        dungeonScreen.getController().setWinScreen(winScreen);
        resetScreen.getController().setDungeonScreen(dungeonScreen);
        dungeonScreen.start();
    }

    @FXML
    public void HandleBack(ActionEvent event) {
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
