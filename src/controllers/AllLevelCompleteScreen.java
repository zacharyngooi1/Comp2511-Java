package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AllLevelCompleteScreen {
    private Stage stage;
    private String title;
    private AllLevelCompleteController controller;
    private Scene scene;

    public AllLevelCompleteScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Game Complete";

        controller = new AllLevelCompleteController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AllLevelCompleteView.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public AllLevelCompleteController getController() {
        return controller;
    }
    public Stage getStage() {
        return this.stage;
    }
}
