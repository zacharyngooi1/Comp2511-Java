package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");

        // Initialises the dungeon controller loader's variables
        DungeonControllerLoader dungeonControllerLoader = new DungeonControllerLoader("all.json");

        DungeonControllerLoader dungeonControllerLoader2 = new DungeonControllerLoader("portals.json");

        // Actually creates the dungeon
        DungeonController controller = dungeonControllerLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
