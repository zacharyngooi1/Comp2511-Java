package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * Loads the model via DungeonLoader. Implements the functions on the view
 * side of things, which involves taking the model's entities, creating
 * corresponding view/UI elements and tying the two together.
 *
 * This class also creates the controller.
 *
 * @author Robert Clifton-Everest
 */
public class DungeonControllerLoader extends DungeonLoader {
    public enum Layer {
        SWITCHES, OTHERS, PICKUPS, ENEMIES, PLAYER, DOORS
    }

    private List<List<ImageView>> viewsByLayer;

    private Image playerImage;
    private Image wallImage;
    private Image boulderImage;
    private Image enemyImage;
    private Image treasureImage;
    private Image exitImage;
    private Image keyImage;
    private Image floorSwitchOnImage;
    private Image floorSwitchOffImage;
    private Image invincibilityImage;
    private Image doorOpenImage;
    private Image doorCloseImage;
    private Image portalImage;
    private Image swordImage;


    public DungeonControllerLoader(String filename) throws FileNotFoundException {
        super(filename);

        viewsByLayer = new ArrayList<List<ImageView>>();
        for (int i = 0; i < Layer.values().length; i++) {
            viewsByLayer.add(new ArrayList<ImageView>());
        }

        playerImage = new Image((new File("images/human_new.png")).toURI().toString());

        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());

        boulderImage = new Image((new File("images/boulder.png")).toURI().toString());

        enemyImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());

        treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());

        exitImage = new Image((new File("images/exit.png")).toURI().toString());

        keyImage = new Image((new File("images/key.png")).toURI().toString());

        floorSwitchOnImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
        floorSwitchOffImage = new Image((new File("images/pressure_plate.png")).toURI().toString());

        invincibilityImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());

        doorOpenImage = new Image((new File("images/open_door.png")).toURI().toString());
        doorCloseImage = new Image((new File("images/closed_door.png")).toURI().toString());

        portalImage = new Image((new File("images/portal.png")).toURI().toString());

        swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        connectEntity(player, view);
        viewsByLayer.get(Layer.PLAYER.ordinal()).add(view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        connectEntity(wall, view);
        viewsByLayer.get(Layer.OTHERS.ordinal()).add(view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        connectEntity(boulder, view);
        viewsByLayer.get(Layer.OTHERS.ordinal()).add(view);
    }

    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        connectEntity(enemy, view);
        viewsByLayer.get(Layer.ENEMIES.ordinal()).add(view);
    }

    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        connectEntity(treasure, view);
        viewsByLayer.get(Layer.PICKUPS.ordinal()).add(view);
    }

    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        connectEntity(exit, view);
        viewsByLayer.get(Layer.OTHERS.ordinal()).add(view);
    }

    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        connectEntity(key, view);
        viewsByLayer.get(Layer.PICKUPS.ordinal()).add(view);
    }

    @Override
    public void onLoad(FloorSwitch floorSwitch) {
        ImageView view = new ImageView(floorSwitchOnImage);
        connectEntity(floorSwitch, view);
        trackStatus(floorSwitch, view, floorSwitchOnImage, floorSwitchOffImage);
        viewsByLayer.get(Layer.SWITCHES.ordinal()).add(view);
    }

    @Override
    public void onLoad(Invincibility invincibility) {
        ImageView view = new ImageView(invincibilityImage);
        connectEntity(invincibility, view);
        viewsByLayer.get(Layer.PICKUPS.ordinal()).add(view);
    }

    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(doorCloseImage);
        connectEntity(door, view);
        trackStatus(door, view, doorOpenImage, doorCloseImage);
        viewsByLayer.get(Layer.DOORS.ordinal()).add(view);
    }

    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        connectEntity(portal, view);
        viewsByLayer.get(Layer.OTHERS.ordinal()).add(view);
    }

    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        connectEntity(sword, view);
        viewsByLayer.get(Layer.PICKUPS.ordinal()).add(view);
    }

    private void connectEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        trackVisibility(entity, view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        // Set the entity to the grid square corresponding to its starting
        // x/y values.
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());

        // Ensure that whenever the entity's x/y values are changed, the
        // entity's grid square changes to match.
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Bind a node's visiblity to an entity's visiblity.
     * @param entity
     * @param node
     */
    private void trackVisibility(Entity entity, Node node) {
        entity.visible().bindBidirectional(node.visibleProperty());
    }

    /**
     * Bind an alternating entity's view to its status.
     * @param entity the alternating entity.
     * @param view the entity's view.
     * @param imageTrue the image for when the entity's status is true.
     * @param imageFalse the image for when the entity's status is false.
     */
    private void trackStatus(AlternatingEntity entity, ImageView view, Image imageTrue, Image imageFalse) {
        entity.status().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    view.setImage(imageTrue);
                } else {
                    view.setImage(imageFalse);
                }
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded views.
     * @return a dungeon controller.
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        Dungeon dungeon = load();

        List<ImageView> viewsByLayerFlattened = new ArrayList<ImageView>();
        for (List<ImageView> layer : viewsByLayer) {
            for (ImageView view : layer) {
                viewsByLayerFlattened.add(view);
            }
        }

        return new DungeonController(dungeon, viewsByLayerFlattened);
    }
}
