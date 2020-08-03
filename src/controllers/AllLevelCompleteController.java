package controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.application.Platform;
import java.io.IOException;



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

    private DungeonScreen dungeonScreen;

    private ResetScreen resetScreen;

    private StartScreen startScreen;

    private LevelSelectScreen levelSelect;

    private HowToPlayScreen howToPlayScreen;

    private WinScreen winScreen;

    private AllLevelCompleteScreen allLevelCompleteScreen;

    private PauseScreen pauseScreen;

    @FXML
    public void handleMainMenu(ActionEvent event) throws IOException{
        if (dungeonScreen != null) {
            DungeonScreen newDungeon = new DungeonScreen(dungeonScreen.getStage());
            StartScreen newstart = new StartScreen(startScreen.getStage());
            LevelSelectScreen newlevelselect = new LevelSelectScreen(levelSelect.getStage());
            HowToPlayScreen newHowToPlay = new HowToPlayScreen(howToPlayScreen.getStage());
            WinScreen newWinScreen = new WinScreen(winScreen.getStage());
            AllLevelCompleteScreen newEndingScreen = new AllLevelCompleteScreen(allLevelCompleteScreen.getStage());
            PauseScreen newPause = new PauseScreen(pauseScreen.getStage());

            newHowToPlay.getController().setStartScreen(newstart);

            newEndingScreen.getController().setStartScreen(newstart);
        
            newlevelselect.getController().setStartScreen(newstart);
            newlevelselect.getController().setDungeonScreen(newDungeon);
            newlevelselect.getController().setResetScreen(resetScreen);
            newlevelselect.getController().setWinScreen(newWinScreen);
            newlevelselect.getController().setPauseScreen(newPause);

            newDungeon.getController().setResetScreen(resetScreen);
            newDungeon.getController().setStartScreen(newstart);
            newDungeon.getController().setWinScreen(newWinScreen);
            newDungeon.getController().setPauseScreen(newPause);

            newWinScreen.getController().setAllLevelComplete(newEndingScreen);
            newWinScreen.getController().setDungeonScreen(newDungeon);
            newWinScreen.getController().setStartScreen(newstart);
            newWinScreen.getController().setPauseScreen(newPause);

            newPause.setDungeonScreen(newDungeon);
            newPause.setStartScreen(newstart);

            newstart.getController().setDungeonScreen(newDungeon);
            newstart.getController().setLevelSelect(newlevelselect);
            newstart.getController().setHowToPlayScreen(newHowToPlay);

            newstart.start();
        }
        Audio.playSound(Audio.buttonPress);
    }

    @FXML
    public void handleExit(ActionEvent event) {
        Platform.exit();    
    }


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

    public void setHowToPlayScreen(HowToPlayScreen howToPlay) {
        this.howToPlayScreen = howToPlay;
    }

    public void setWinScreen(WinScreen winscreen) {
        this.winScreen = winscreen;
    }

    public void setAllLevelComplete(AllLevelCompleteScreen endingscreen) {
        this.allLevelCompleteScreen = endingscreen;
    }

    public void setPauseScreen(PauseScreen pausescreen) {
        this.pauseScreen = pausescreen;
    }
}
