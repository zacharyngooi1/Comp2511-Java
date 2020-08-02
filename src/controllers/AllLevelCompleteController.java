package controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * A JavaFX controller for the dungeon.
 *
 * @author Robert Clifton-Everest
 */
public class AllLevelCompleteController{
    @FXML
    private Button MainMenu;

    private StartScreen startScreen;

    @FXML
    public void HandleMainMenu(ActionEvent event) {
        startScreen.start();
    }

    public void setStartScreen(StartScreen startscreen) {
        this.startScreen = startscreen;
    }
}
