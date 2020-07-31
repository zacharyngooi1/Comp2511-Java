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

    @FXML
    private Button retry;
    
    private DungeonScreen dungeonScreen;

    private ResetScreen resetScreen;

    private StartScreen startScreen;

    private LevelSelectScreen levelSelect;



    @FXML void RetryAction(ActionEvent event) throws IOException {

        if (dungeonScreen != null) {
            DungeonScreen newDungeon = new DungeonScreen(dungeonScreen.getStage());
            StartScreen newstart = new StartScreen(startScreen.getStage());
            LevelSelectScreen newlevelselect = new LevelSelectScreen(levelSelect.getStage());

            newDungeon.SetStage(dungeonScreen.getInt());

            newlevelselect.getController().setStartScreen(newstart);
            newlevelselect.getController().setDungeonScreen(newDungeon);
            newlevelselect.getController().setResetScreen(resetScreen);

            newDungeon.getController().setResetScreen(resetScreen);
            newDungeon.getController().setStartScreen(newstart);

            newstart.getController().setDungeonScreen(newDungeon);
            newstart.getController().setLevelSelect(newlevelselect);

            newDungeon.start();
        	System.out.println("!!!!!!DUNGEON RESET!!!!!!");
    	} else System.out.println("!!!!!!DUNGEON UNABLE TO RESET!!!!!!");
    }

    @FXML
    public void ResetAction(ActionEvent event) throws IOException {
        
    	if (dungeonScreen != null) {
            DungeonScreen newDungeon = new DungeonScreen(dungeonScreen.getStage());
            StartScreen newstart = new StartScreen(startScreen.getStage());
            LevelSelectScreen newlevelselect = new LevelSelectScreen(levelSelect.getStage());

            newlevelselect.getController().setStartScreen(newstart);
            newlevelselect.getController().setDungeonScreen(newDungeon);
            newlevelselect.getController().setResetScreen(resetScreen);

            newDungeon.getController().setResetScreen(resetScreen);
            newDungeon.getController().setStartScreen(newstart);

            newstart.getController().setDungeonScreen(newDungeon);
            newstart.getController().setLevelSelect(newlevelselect);

            newstart.start();
        	System.out.println("!!!!!!DUNGEON RESET!!!!!!");
    	} else System.out.println("!!!!!!DUNGEON UNABLE TO RESET!!!!!!");
    }

    /**
	 * @param dungeonScreen the dungeonScreen to set
	 */
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
    }
    
    public void setLevelSelect(LevelSelectScreen screen) {
        this.levelSelect = screen;
    }

    public void setStartScreen(StartScreen startscreen) {
        this.startScreen = startscreen;
    }

    public void setResetScreen(ResetScreen screen) {
		this.resetScreen = screen;
    }

}