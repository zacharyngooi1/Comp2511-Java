package controllers;

import model.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

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

    // A map between entities and their views that should always be 1:1.
    private HashMap<Entity, ImageView> entitiesViews;

    public DungeonControllerLoader(String filename) throws FileNotFoundException {
        super(filename);

        viewsByLayer = new ArrayList<List<ImageView>>();

        for (int i = 0; i < Layer.values().length; i++) {
            viewsByLayer.add(new ArrayList<ImageView>());
        }

        entitiesViews = new HashMap<>();
    }

    @Override
    public void onLoad(Player player) {
        ImageView view = new ImageView(Images.playerIdle1);
        connectEntity(player, view);
        viewsByLayer.get(Layer.PLAYER.ordinal()).add(view);
        entitiesViews.put(player, view);

        AnimationController animationController = new AnimationController(view);

        List<Image> animationCycleImages = new ArrayList<>();
        animationCycleImages.add(Images.playerIdle1);
        animationCycleImages.add(Images.playerIdle2);
        AnimationCycle animationCycle = new AnimationCycle(animationController, 250, animationCycleImages);

        AnimationStatic attack = new AnimationStatic(animationController, 500, Images.playerAttack);
        AnimationStatic pickup = new AnimationStatic(animationController, 500, Images.playerPickup);
        AnimationStatic teleport = new AnimationStatic(animationController, 1000, Images.playerTeleport);

        attack.setNext(animationCycle);
        pickup.setNext(animationCycle);
        teleport.setNext(animationCycle);

        animationController.registerTransition("attack", attack);
        animationController.registerTransition("pickup", pickup);
        animationController.registerTransition("teleport", teleport);

        animationController.start(animationCycle);
        player.setAnimationController(animationController);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(Images.wall);
        connectEntity(wall, view);
        viewsByLayer.get(Layer.OTHERS.ordinal()).add(view);
        entitiesViews.put(wall, view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(Images.boulder);
        connectEntity(boulder, view);
        viewsByLayer.get(Layer.OTHERS.ordinal()).add(view);
        entitiesViews.put(boulder, view);
    }

    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(Images.enemyIdle1);
        connectEntity(enemy, view);
        viewsByLayer.get(Layer.ENEMIES.ordinal()).add(view);
        entitiesViews.put(enemy, view);

        AnimationController animationController = new AnimationController(view);

        List<Image> animationCycleImages = new ArrayList<>();
        animationCycleImages.add(Images.enemyIdle1);
        animationCycleImages.add(Images.enemyIdle2);
        AnimationCycle animationCycle = new AnimationCycle(animationController, 250, animationCycleImages);

        AnimationStatic attack = new AnimationStatic(animationController, 500, Images.enemyAttack);
        AnimationStatic teleport = new AnimationStatic(animationController, 1000, Images.enemyTeleport);

        attack.setNext(animationCycle);
        teleport.setNext(animationCycle);

        animationController.registerTransition("attack", attack);
        animationController.registerTransition("teleport", teleport);

        animationController.start(animationCycle);
        enemy.setAnimationController(animationController);
    }

    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(Images.treasure);
        connectEntity(treasure, view);
        viewsByLayer.get(Layer.PICKUPS.ordinal()).add(view);
        entitiesViews.put(treasure, view);
    }

    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(Images.exit);
        connectEntity(exit, view);
        viewsByLayer.get(Layer.OTHERS.ordinal()).add(view);
        entitiesViews.put(exit, view);
    }

    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(Images.key);
        connectEntity(key, view);
        viewsByLayer.get(Layer.PICKUPS.ordinal()).add(view);
        entitiesViews.put(key, view);
    }

    @Override
    public void onLoad(FloorSwitch floorSwitch) {
        ImageView view = new ImageView(Images.floorSwitchOff);
        connectEntity(floorSwitch, view);
        trackStatus(floorSwitch, view, Images.floorSwitchOn, Images.floorSwitchOff);
        viewsByLayer.get(Layer.SWITCHES.ordinal()).add(view);
        entitiesViews.put(floorSwitch, view);
    }

    @Override
    public void onLoad(Invincibility invincibility) {
        ImageView view = new ImageView(Images.invincibility);
        connectEntity(invincibility, view);
        viewsByLayer.get(Layer.PICKUPS.ordinal()).add(view);
        entitiesViews.put(invincibility, view);
    }

    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(Images.doorClose);
        connectEntity(door, view);
        trackStatus(door, view, Images.doorOpen, Images.doorClose);
        viewsByLayer.get(Layer.DOORS.ordinal()).add(view);
        entitiesViews.put(door, view);
    }

    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(Images.portal);
        connectEntity(portal, view);
        viewsByLayer.get(Layer.OTHERS.ordinal()).add(view);
        entitiesViews.put(portal, view);
    }

    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(Images.sword);
        connectEntity(sword, view);
        viewsByLayer.get(Layer.PICKUPS.ordinal()).add(view);
        entitiesViews.put(sword, view);
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

        return new DungeonController(dungeon, viewsByLayerFlattened, entitiesViews);
    }
}
