package controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.application.Platform;


/**
 * A JavaFX controller for the dungeon.
 *
 * @author Robert Clifton-Everest
 */
public class AllLevelCompleteController{
    @FXML
    private Button mainMenu;
    
    @FXML
    private Button exit;

    private StartScreen startScreen;

    @FXML
    public void handleMainMenu(ActionEvent event) {
        startScreen.start();
    }

    @FXML
    public void handleExit(ActionEvent event) {
        Platform.exit();    
    }


    public void setStartScreen(StartScreen startscreen) {
        this.startScreen = startscreen;
    }
}
