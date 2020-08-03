package controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        StartScreen startScreen = new StartScreen(primaryStage);
        DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
        ResetScreen resetScreen = new ResetScreen(primaryStage);
        LevelSelectScreen levelSelectScreen = new LevelSelectScreen(primaryStage);
        HowToPlayScreen howToPlayScreen = new HowToPlayScreen(primaryStage);
        WinScreen winScreen = new WinScreen(primaryStage);
        AllLevelCompleteScreen allLevelComplete = new AllLevelCompleteScreen(primaryStage);
        PauseScreen pauseScreen = new PauseScreen(primaryStage);

        allLevelComplete.getController().setStartScreen(startScreen);

        howToPlayScreen.getController().setStartScreen(startScreen);

        resetScreen.getController().setResetScreen(resetScreen);
        resetScreen.getController().setDungeonScreen(dungeonScreen);
        resetScreen.getController().setStartScreen(startScreen);
        resetScreen.getController().setLevelSelect(levelSelectScreen);
        resetScreen.getController().setHowToPlayScreen(howToPlayScreen);
        resetScreen.getController().setWinScreen(winScreen);
        resetScreen.getController().setAllLevelComplete(allLevelComplete);
        resetScreen.getController().setPauseScreen(pauseScreen);

        dungeonScreen.getController().setStartScreen(startScreen);
        dungeonScreen.getController().setResetScreen(resetScreen);
        dungeonScreen.getController().setWinScreen(winScreen);
        dungeonScreen.getController().setPauseScreen(pauseScreen);

        startScreen.getController().setDungeonScreen(dungeonScreen);
        startScreen.getController().setLevelSelect(levelSelectScreen);
        startScreen.getController().setHowToPlayScreen(howToPlayScreen);

        levelSelectScreen.getController().setDungeonScreen(dungeonScreen);
        levelSelectScreen.getController().setStartScreen(startScreen);
        levelSelectScreen.getController().setResetScreen(resetScreen);
        levelSelectScreen.getController().setWinScreen(winScreen);
        levelSelectScreen.getController().setPauseScreen(pauseScreen);

        winScreen.getController().setDungeonScreen(dungeonScreen);
        winScreen.getController().setStartScreen(startScreen);
        winScreen.getController().setResetScreen(resetScreen);
        winScreen.getController().setWinScreen(winScreen);
        winScreen.getController().setAllLevelComplete(allLevelComplete);
        winScreen.getController().setPauseScreen(pauseScreen);

        pauseScreen.setDungeonScreen(dungeonScreen);
        pauseScreen.setStartScreen(startScreen);
        pauseScreen.setPauseScreen(pauseScreen);

        startScreen.start();
        Audio.playSound(Audio.bgm, true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
