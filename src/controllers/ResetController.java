package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

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

    private HowToPlayScreen howToPlayScreen;

    private WinScreen winScreen;

    private AllLevelCompleteScreen allLevelCompleteScreen;

    @FXML void RetryAction(ActionEvent event) throws IOException {
        if (dungeonScreen != null) {
            DungeonScreen newDungeon = new DungeonScreen(dungeonScreen.getStage());
            StartScreen newstart = new StartScreen(startScreen.getStage());
            LevelSelectScreen newlevelselect = new LevelSelectScreen(levelSelect.getStage());
            WinScreen newWinScreen = new WinScreen(winScreen.getStage());
            AllLevelCompleteScreen newEndingScreen = new AllLevelCompleteScreen(allLevelCompleteScreen.getStage());

            newWinScreen.getController().setAllLevelComplete(newEndingScreen);

            newDungeon.SetStage(dungeonScreen.getInt());

            newlevelselect.getController().setStartScreen(newstart);
            newlevelselect.getController().setDungeonScreen(newDungeon);
            newlevelselect.getController().setResetScreen(resetScreen);
            newlevelselect.getController().setWinScreen(newWinScreen);

            newDungeon.getController().setResetScreen(resetScreen);
            newDungeon.getController().setStartScreen(newstart);
            newDungeon.getController().setWinScreen(newWinScreen);

            newstart.getController().setDungeonScreen(newDungeon);
            newstart.getController().setLevelSelect(newlevelselect);

            newDungeon.start();
        }
    }

    @FXML
    public void ResetAction(ActionEvent event) throws IOException {
        if (dungeonScreen != null) {
            DungeonScreen newDungeon = new DungeonScreen(dungeonScreen.getStage());
            StartScreen newstart = new StartScreen(startScreen.getStage());
            LevelSelectScreen newlevelselect = new LevelSelectScreen(levelSelect.getStage());
            HowToPlayScreen newHowToPlay = new HowToPlayScreen(howToPlayScreen.getStage());
            WinScreen newWinScreen = new WinScreen(winScreen.getStage());
            AllLevelCompleteScreen newEndingScreen = new AllLevelCompleteScreen(allLevelCompleteScreen.getStage());

            newWinScreen.getController().setAllLevelComplete(newEndingScreen);

            newHowToPlay.getController().setStartScreen(newstart);

            newlevelselect.getController().setStartScreen(newstart);
            newlevelselect.getController().setDungeonScreen(newDungeon);
            newlevelselect.getController().setResetScreen(resetScreen);
            newlevelselect.getController().setWinScreen(newWinScreen);

            newDungeon.getController().setResetScreen(resetScreen);
            newDungeon.getController().setStartScreen(newstart);
            newDungeon.getController().setWinScreen(newWinScreen);

            newstart.getController().setDungeonScreen(newDungeon);
            newstart.getController().setLevelSelect(newlevelselect);
            newstart.getController().setHowToPlayScreen(newHowToPlay);

            newstart.start();
        }
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

    public void setHowToPlayScreen(HowToPlayScreen howToPlay) {
        this.howToPlayScreen = howToPlay;
    }

    public void setWinScreen(WinScreen winscreen) {
        this.winScreen = winscreen;
    }

    public void setAllLevelComplete(AllLevelCompleteScreen endingscreen) {
        this.allLevelCompleteScreen = endingscreen;
    }
}
