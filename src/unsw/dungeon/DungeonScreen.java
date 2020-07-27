package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {

    private Stage stage;
    private String title;
    private DungeonController controller;
    private DungeonControllerLoader dungeonControllerLoader;
    private Scene scene;

    public DungeonScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "dungeon";

        dungeonControllerLoader = new DungeonControllerLoader("all.json");

        // Actually creates the dungeon
        controller = dungeonControllerLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root);    
        root.requestFocus(); 
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public DungeonController getController() {
        return controller;
    }

    public Stage getStage() {
        return this.stage;
    }
}
