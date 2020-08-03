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
    private Button mainMenu;

    private StartScreen startScreen;

    @FXML
    public void handleMainMenu(ActionEvent event) {
        startScreen.start();
        Audio.playSound(Audio.buttonPress);
    }

    public void setStartScreen(StartScreen startscreen) {
        this.startScreen = startscreen;
    }
}
