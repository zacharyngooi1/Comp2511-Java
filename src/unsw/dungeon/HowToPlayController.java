package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;


import java.io.File;

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
        System.out.println("Go back pressed!");
        startScreen.start();
    }

    public void setStartScreen(StartScreen wantedStartScreen) {
        this.startScreen = wantedStartScreen;
    }
}