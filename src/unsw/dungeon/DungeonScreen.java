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
    private int current_stage;

    public DungeonScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "dungeon";
        this.current_stage = 1;

        dungeonControllerLoader = new DungeonControllerLoader("maze.json");

        // Actually creates the dungeon
        controller = dungeonControllerLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root);    
        root.requestFocus(); 
    }

    public void start() {
        this.stage.setTitle(title);
        this.stage.setScene(scene);
        this.stage.show();
    }
    

    public DungeonController getController() {
        return controller;
    }

    public Stage getStage() {
        return this.stage;
    }

    public int getInt() {
        return this.current_stage;
    }

    public void setInt(int newStageInt) {
        this.current_stage = newStageInt;
    }

    public void SetStage(int i) throws IOException{
        title = "dungeon";
        String level;
        if (i == 1) {
            level = "maze.json";
            this.current_stage = 1;
        }
        else if (i == 2) {
            level = "advanced.json";
            this.current_stage = 2;
        }
        else if (i == 3) {
            level = "all.json";
            this.current_stage = 3;
        }
        else {
            level = "maze.json";
            current_stage = 1;
        }

        dungeonControllerLoader = new DungeonControllerLoader(level);

        // Actually creates the dungeon
        controller = dungeonControllerLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root);    
        root.requestFocus(); 

        this.stage.setTitle(title);
        this.stage.setScene(scene);
    }

}
