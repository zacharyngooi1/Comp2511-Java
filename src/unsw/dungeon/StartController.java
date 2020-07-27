package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;


import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 *
 * @author Robert Clifton-Everest
 */
public class StartController{
    @FXML
    private Button play;
    
    private DungeonScreen DungeonScreen;

    @FXML
	public void handlePlay(ActionEvent event) {
        System.out.println("Play pressed!");
        DungeonScreen.start();
    }

    public void setDungeonScreen(DungeonScreen dungeonscreen) {
        this.DungeonScreen = dungeonscreen;
    }

    
}