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

        allLevelComplete.getController().setStartScreen(startScreen);

        winScreen.getController().setDungeonScreen(dungeonScreen);
        winScreen.getController().setStartScreen(startScreen);
        winScreen.getController().setResetScreen(resetScreen);
        winScreen.getController().setWinScreen(winScreen);
        winScreen.getController().setAllLevelComplete(allLevelComplete);

        howToPlayScreen.getController().setStartScreen(startScreen);

        resetScreen.getController().setResetScreen(resetScreen);
        resetScreen.getController().setDungeonScreen(dungeonScreen);
        resetScreen.getController().setStartScreen(startScreen);
        resetScreen.getController().setLevelSelect(levelSelectScreen);
        resetScreen.getController().setHowToPlayScreen(howToPlayScreen);
        resetScreen.getController().setWinScreen(winScreen);
        resetScreen.getController().setAllLevelComplete(allLevelComplete);

        dungeonScreen.getController().setStartScreen(startScreen);
        dungeonScreen.getController().setResetScreen(resetScreen);
        dungeonScreen.getController().setWinScreen(winScreen);

        startScreen.getController().setDungeonScreen(dungeonScreen);
        startScreen.getController().setLevelSelect(levelSelectScreen);
        startScreen.getController().setHowToPlayScreen(howToPlayScreen);

        levelSelectScreen.getController().setDungeonScreen(dungeonScreen);
        levelSelectScreen.getController().setStartScreen(startScreen);
        levelSelectScreen.getController().setResetScreen(resetScreen);
        levelSelectScreen.getController().setWinScreen(winScreen);

        startScreen.start();
        Audio.playSound(Audio.bgm, true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
