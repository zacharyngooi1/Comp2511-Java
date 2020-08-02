package controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * A JavaFX controller for the dungeon.
 *
 * @author Robert Clifton-Everest
 */
public class HowToPlayController{
    @FXML
    private Button back;

    private StartScreen startScreen;

    @FXML
    public void handleBack(ActionEvent event) {
        startScreen.start();
    }

    public void setStartScreen(StartScreen wantedStartScreen) {
        this.startScreen = wantedStartScreen;
    }
}
