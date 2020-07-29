package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * A JavaFX controller for the dungeon.
 *
 * @author Robert Clifton-Everest
 */
public class DungeonController {
    @FXML
    private GridPane squares;

    @FXML
    private ProgressBar swordProgress;

    @FXML
    private Label swordLabel;

    @FXML
    private ProgressBar invincibilityProgress;

    @FXML
    private Label invincibilityLabel;

    @FXML
    private GridPane inventory;

    @FXML
    private TextArea goalText;

    private List<ImageView> initialViews;
    private Player player;
    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialViews) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialViews = new ArrayList<>(initialViews);
    }

    @FXML
    public void initialize() {
        Image ground = DungeonControllerLoader.createImage("dirt.png");

        // Add the ground first so it is below all other views.
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialViews) {
            squares.getChildren().add(entity);
        }

        updatePlayerUI();
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                player.moveUp();
                break;
            case DOWN:
                player.moveDown();
                break;
            case LEFT:
                player.moveLeft();
                break;
            case RIGHT:
                player.moveRight();
                break;
            default:
                break;
        }

        updatePlayerUI();
    }

    private void updatePlayerUI() {
        updateConsumable(player.getSword(), swordProgress, swordLabel);
        updateConsumable(player.getInvincibility(), invincibilityProgress, invincibilityLabel);
    }

    private void updateConsumable(ConsumableEntity consumable, ProgressBar progressBar, Label label) {
        if (consumable == null) {
            label.setText("");
            progressBar.setProgress(0);
        } else {
            progressBar.setProgress((float) consumable.getValue() / consumable.getMaxValue());
            label.setText(consumable.getValue() + "/" + consumable.getMaxValue());
        }
    }

    // public void updateInventory

    private void updateGoalText(String goalString) {
        goalText.setText(goalString);
    }
}
