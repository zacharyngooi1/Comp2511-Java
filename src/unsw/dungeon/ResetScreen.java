package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ResetScreen {

    private Stage stage;
    private String title;
    private ResetController controller;
    private Scene scene;

    public ResetScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Retry Screen";

        controller = new ResetController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOverView.fxml"));
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

    public ResetController getController() {
        return controller;
    }

    public Stage getStage() {
        return this.stage;
    }
}