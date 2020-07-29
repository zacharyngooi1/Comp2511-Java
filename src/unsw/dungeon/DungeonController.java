package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.Node;

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

    private Dungeon dungeon;
    private Player player;
    private List<ImageView> initialViews;

    private final int inventoryWidth = 4;

    // A map between entities and their views that should always be 1:1.
    private HashMap<Entity, ImageView> entitiesViews;

    // Maps between IDs and hues for various entities.
    private HashMap<Integer, Double> doorKeyIDsHues;
    private HashMap<Integer, Double> portalIDsHues;

    public DungeonController(Dungeon dungeon, List<ImageView> initialViews, HashMap<Entity, ImageView> entitiesViews) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialViews = new ArrayList<>(initialViews);
        this.doorKeyIDsHues = new HashMap<>();
        this.portalIDsHues = new HashMap<>();
        this.entitiesViews = new HashMap<>(entitiesViews);
    }

    @FXML
    public void initialize() {
        // Add the ground first so it is below all other views.
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(Images.dirtImage), x, y);
            }
        }

        for (ImageView entity : initialViews) {
            squares.getChildren().add(entity);
        }

        setupHueMaps();
        applyHueMaps();
        updateUI();
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

        updateUI();
    }

    /**
     * Setup doorKeyIDsHues and portalIDsHues by evenly distributing
     * hue values across the number of unique door/key IDs and portal IDs
     * respectively.
     */
    private void setupHueMaps() {
        double hueOffset = 0;
        HashSet<Integer> keyIDs = new HashSet<>();

        for (Key key : dungeon.getKeys()) {
            keyIDs.add(key.getID());
        }

        for (Integer keyID : keyIDs) {
            doorKeyIDsHues.put(keyID, hueOffset);
            hueOffset += 1.0 / keyIDs.size();
        }

        hueOffset = 0;
        HashSet<Integer> portalIDs = new HashSet<>();

        for (Portal portal : dungeon.getPortals()) {
            portalIDs.add(portal.getID());
        }

        for (Integer portalID : portalIDs) {
            portalIDsHues.put(portalID, hueOffset);
            hueOffset += 1.0 / portalIDs.size();
        }
    }

    private void applyHueMaps() {
        for (Entity entity : entitiesViews.keySet()) {
            switch (entity.getTag()) {
                case KEY:
                    Key key = (Key) entity;
                    setHue(entitiesViews.get(entity), doorKeyIDsHues.get(key.getID()));
                    System.out.println(doorKeyIDsHues.get(key.getID()));
                    break;
                case DOOR:
                    Door door = (Door) entity;
                    setHue(entitiesViews.get(entity), doorKeyIDsHues.get(door.getID()));
                    break;
                case PORTAL:
                    Portal portal = (Portal) entity;
                    setHue(entitiesViews.get(entity), portalIDsHues.get(portal.getID()));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Sets the hue of a given ImageView.
     */
    private void setHue(ImageView imageView, double hue) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(hue);
        imageView.setEffect(colorAdjust);
    }

    private void updateUI() {
        updateConsumableUI();
        updateInventoryUI();
        updateGoalUI();
    }

    private void updateConsumableUI() {
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

    private void updateInventoryUI() {
        // Clear the inventory.
        for (Node node : new ArrayList<Node>(inventory.getChildren())) {
            inventory.getChildren().remove(node);
        }

        List<Key> keys = new ArrayList<>(player.getKeys());
        List<Treasure> treasures = new ArrayList<>(player.getTreasures());

        // At the moment the inventory only consists of keys.
        for (int y = 0; y < inventory.getHeight(); y++) {
            for (int x = 0; x < inventoryWidth; x++) {
                if (!keys.isEmpty()) {
                    ImageView imageView = new ImageView(Images.keyImage);
                    Key nextKey = keys.remove(0);
                    setHue(imageView, doorKeyIDsHues.get(nextKey.getID()));
                    inventory.add(imageView, x, y);
                } else if (!treasures.isEmpty()) {
                    ImageView imageView = new ImageView(Images.treasureImage);
                    treasures.remove(0);
                    inventory.add(imageView, x, y);
                }
            }
        }
    }

    private void updateGoalUI() {
        goalText.setText(dungeon.getGoalString());
    }
}
