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
    private Button level1;

    @FXML
    private Button level2;

    @FXML
    private Button level3;

    @FXML
    private Button back;

    private DungeonScreen dungeonScreen;

    private StartScreen startScreen;

    private ResetScreen resetScreen;

    private WinScreen winScreen;

    private PauseScreen pauseScreen;

    @FXML
    public void handleLevel1(ActionEvent event) throws IOException {
        dungeonScreen.setStage(1);
        dungeonScreen.getController().setResetScreen(resetScreen);
        dungeonScreen.getController().setStartScreen(startScreen);
        dungeonScreen.getController().setWinScreen(winScreen);
        resetScreen.getController().setDungeonScreen(dungeonScreen);
        dungeonScreen.getController().setPauseScreen(pauseScreen);
        pauseScreen.setDungeonScreen(dungeonScreen);
        pauseScreen.setStartScreen(startScreen);
        dungeonScreen.start();
    }

    @FXML
    public void handleLevel2(ActionEvent event) throws IOException {
        dungeonScreen.setStage(2);
        dungeonScreen.getController().setResetScreen(resetScreen);
        dungeonScreen.getController().setStartScreen(startScreen);
        dungeonScreen.getController().setWinScreen(winScreen);
        resetScreen.getController().setDungeonScreen(dungeonScreen);
        dungeonScreen.getController().setPauseScreen(pauseScreen);
        pauseScreen.setDungeonScreen(dungeonScreen);
        pauseScreen.setStartScreen(startScreen);
        dungeonScreen.start();
    }

    @FXML
    public void handleLevel3(ActionEvent event) throws IOException {
        dungeonScreen.setStage(3);
        dungeonScreen.getController().setResetScreen(resetScreen);
        dungeonScreen.getController().setStartScreen(startScreen);
        dungeonScreen.getController().setWinScreen(winScreen);
        dungeonScreen.getController().setPauseScreen(pauseScreen);
        resetScreen.getController().setDungeonScreen(dungeonScreen);
        pauseScreen.setDungeonScreen(dungeonScreen);
        pauseScreen.setStartScreen(startScreen);
        dungeonScreen.start();
    }

    @FXML
    public void handleBack(ActionEvent event) {
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

    public void setPauseScreen(PauseScreen pausescreen) {
        this.pauseScreen = pausescreen;
    }
}
