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
    private Button GoBack;

    private StartScreen startScreen;

    @FXML
    public void HandleGoBack(ActionEvent event) {
        startScreen.start();
    }

    public void setStartScreen(StartScreen wantedStartScreen) {
        this.startScreen = wantedStartScreen;
    }
}
