package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;



import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 *
 * @author Robert Clifton-Everest
 */
public class DungeonController {
    @FXML
    private GridPane squares;

    private List<ImageView> initialViews;

    private Player player;

    private Dungeon dungeon;

    private StartScreen startScreen;

    private DungeonScreen dungeonscreen;

    private ResetScreen resetScreen;

    private WinScreen winScreen;

    
    public DungeonController(Dungeon dungeon, List<ImageView> initialViews) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialViews = new ArrayList<>(initialViews);
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());
        // Add the ground first so it is below all other views.
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialViews) {
            squares.getChildren().add(entity);
        }

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                player.moveUp();
                if (!player.getALiveStatus()){
                    resetScreen.start();
                }
                else if (dungeon.checkGameWon()) {
                    winScreen.start();
                }
                break;
            case DOWN:
                player.moveDown();
                if (!player.getALiveStatus()) {
                    resetScreen.start();
                }
                else if (dungeon.checkGameWon()) {
                    winScreen.start();
                }
                break;
            case LEFT:
                player.moveLeft();
                if (!player.getALiveStatus()) {
                    resetScreen.start();
                }
                else if (dungeon.checkGameWon()) {
                    winScreen.start();
                }
                break;
            case RIGHT:
                player.moveRight();
                if (!player.getALiveStatus()) {
                    resetScreen.start();
                }
                else if (dungeon.checkGameWon()) {
                    winScreen.start();
                }
                break;
            case ESCAPE:
                startScreen.start();
                break;
            default:
                break;
        }
    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }

    public void setDungeonScreen(DungeonScreen newScreen) {
        this.dungeonscreen = newScreen;
    }

    public void setResetScreen(ResetScreen screen) {
        this.resetScreen = screen;
    }

    public void setWinScreen(WinScreen winscreen) {
        this.winScreen = winscreen;
    }

}
