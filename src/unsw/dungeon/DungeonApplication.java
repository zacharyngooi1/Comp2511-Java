package unsw.dungeon;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        StartScreen startScreen = new StartScreen(primaryStage);
        DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
        ResetScreen resetScreen = new ResetScreen(primaryStage);
        resetScreen.getController().setResetScreen(resetScreen);

        dungeonScreen.getController().setStartScreen(startScreen);
        startScreen.getController().setDungeonScreen(dungeonScreen);
        dungeonScreen.getController().setResetScreen(resetScreen);
        resetScreen.getController().setDungeonScreen(dungeonScreen);
        startScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
