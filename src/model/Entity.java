package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {
    public enum Tag {
        PLAYER, WALL, EXIT, TREASURE, DOOR, KEY, BOULDER, FLOORSWITCH, PORTAL,
        ENEMY, SWORD, INVINCIBILITY
    }

    private IntegerProperty x, y;
    private BooleanProperty visible;
    private Tag tag;
    private CollisionLayer collisionLayer;
    protected Dungeon dungeon;

    /**
     * Create an entity positioned in square (x, y).
     * @param x
     * @param y
     * @param tag
     * @param isCollidable
     */
    public Entity(int x, int y, Tag tag, CollisionLayer collisionLayer, Dungeon dungeon) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.visible = new SimpleBooleanProperty(true);
        this.tag = tag;
        this.collisionLayer = collisionLayer;
        this.dungeon = dungeon;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public BooleanProperty visible() {
        return visible;
    }

    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public Tag getTag() {
        return tag;
    }

    public CollisionLayer getCollisionLayer() {
        return collisionLayer;
    }

    public void setX(int x) {
        this.x().set(x);
    }

    public void setY(int y) {
        this.y().set(y);
    }

    public void setInvisible() {
        visible().set(false);
    }

    public void setCollisionLayer(CollisionLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public boolean collidesWith(Entity other) {
        return collisionLayer.collidesWith(other.getCollisionLayer());
    }

    public void removeFromDungeon() {
        System.out.println("Removing " + getTag());
        setInvisible();
        dungeon.removeEntity(this);
    }

    /**
     * Called whenever another entity enters the same square as this entity.
     * @param other the other entity that has just been imposed on this
     * entity's square.
     */
    public void onEntityEnter(Entity other) {
        System.out.println(other.getTag() + " entered " + getTag());
    }

     /**
     * Called whenever another entity exits the same square as this entity.
     * @param other the other entity that has just left this entity's square.
     */
    public void onEntityExit(Entity other) {
        System.out.println(other.getTag() + " exited " + getTag());
    }
}
