package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

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
public class ResetController{
    @FXML
    private Button Tryagain;
    
    private DungeonScreen dungeonScreen;

    private ResetScreen ResetScreen;

    @FXML
    public void ResetAction(ActionEvent event) throws IOException {
    	if (dungeonScreen != null) {
            DungeonScreen newDungeon = new DungeonScreen(dungeonScreen.getStage());
            newDungeon.getController().setDungeonScreen(newDungeon);
            newDungeon.getController().setResetScreen(ResetScreen);
    		newDungeon.start();
        	System.out.println("!!!!!!DUNGEON RESET!!!!!!");
    	} else System.out.println("!!!!!!DUNGEON UNABLE TO RESET!!!!!!");
    }

    /**
	 * @param dungeonScreen the dungeonScreen to set
	 */
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
    }
    
    public void setResetScreen(ResetScreen screen) {
        this.ResetScreen = screen;
    }

}