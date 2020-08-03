package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

public class PauseScreen {
    private Stage stage;
    private String title;
    private Scene scene;

    private DungeonScreen dungeonScreen;

    private StartScreen startScreen; 

    private PauseScreen pauseScreen;

    public PauseScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "paused";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/PauseView.fxml"));


        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }
    

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        stage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                startScreen.start();
            }
            if (KeyCode.R == event.getCode()) {
                dungeonScreen.start();
            }
        });
    }

    public Stage getStage() {
        return this.stage;
    }

    public void setDungeonScreen(DungeonScreen dungeonscreen) {
        this.dungeonScreen = dungeonscreen;
    }

    public void setStartScreen(StartScreen screen) {
        this.startScreen = screen;
    }

    public void setPauseScreen(PauseScreen pausescreen) {
        this.pauseScreen = pausescreen;
    }

}
