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
    private Button mainMenu;

    @FXML
    private Button retry;

    private DungeonScreen dungeonScreen;

    private ResetScreen resetScreen;

    private StartScreen startScreen;

    private LevelSelectScreen levelSelect;

    private HowToPlayScreen howToPlayScreen;

    private WinScreen winScreen;

    private AllLevelCompleteScreen allLevelCompleteScreen;

    private PauseScreen pauseScreen;

    @FXML
    public void handleRetry(ActionEvent event) throws IOException {
        if (dungeonScreen != null) {
            DungeonScreen newDungeon = new DungeonScreen(dungeonScreen.getStage());
            StartScreen newstart = new StartScreen(startScreen.getStage());
            LevelSelectScreen newlevelselect = new LevelSelectScreen(levelSelect.getStage());
            AllLevelCompleteScreen newEndingScreen = new AllLevelCompleteScreen(allLevelCompleteScreen.getStage());
            PauseScreen newPause = new PauseScreen(pauseScreen.getStage());
            System.out.println("winscreen one" + winScreen);
            newDungeon.setStage(dungeonScreen.getInt());
            
            newEndingScreen.getController().setStartScreen(newstart);

            winScreen.getController().setAllLevelComplete(newEndingScreen);
            winScreen.getController().setDungeonScreen(newDungeon);
            winScreen.getController().setResetScreen(resetScreen);
            winScreen.getController().setStartScreen(newstart);

            newlevelselect.getController().setStartScreen(newstart);
            newlevelselect.getController().setDungeonScreen(newDungeon);
            newlevelselect.getController().setResetScreen(resetScreen);
            newlevelselect.getController().setWinScreen(winScreen);
            newlevelselect.getController().setPauseScreen(newPause);

            newDungeon.getController().setResetScreen(this.resetScreen);
            newDungeon.getController().setStartScreen(newstart);
            newDungeon.getController().setWinScreen(winScreen);
            newDungeon.getController().setPauseScreen(newPause);

            newstart.getController().setDungeonScreen(newDungeon);
            newstart.getController().setLevelSelect(newlevelselect);

            newPause.setDungeonScreen(newDungeon);
            newPause.setStartScreen(newstart);
            System.out.println("winscreen one" + winScreen);

            newDungeon.start();
        }

        Audio.playSound(Audio.buttonPress);
    }

    @FXML
    public void handleMainMenu(ActionEvent event) throws IOException {
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

            newPause.setDungeonScreen(newDungeon);
            newPause.setStartScreen(newstart);

            newstart.getController().setDungeonScreen(newDungeon);
            newstart.getController().setLevelSelect(newlevelselect);
            newstart.getController().setHowToPlayScreen(newHowToPlay);

            newstart.start();
        }

        Audio.playSound(Audio.buttonPress);
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

    public void setPauseScreen(PauseScreen pausescreen) {
        this.pauseScreen = pausescreen;
    }
}
