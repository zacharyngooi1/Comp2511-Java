package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed
    private IntegerProperty x, y;
    private Tag tag;
    private boolean isCollidable;
    protected Dungeon dungeon;

    /**
     * Create an entity positioned in square (x, y).
     * @param x
     * @param y
     * @param tag
     * @param isCollidable
     */
    public Entity(int x, int y, Tag tag, boolean isCollidable, Dungeon dungeon) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.tag = tag;
        this.isCollidable = isCollidable;
        this.dungeon = dungeon;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
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

    public boolean isCollidable() {
        return isCollidable;
    }

    public void setX(int x) {
        this.x().set(x);
    }

    public void setY(int y) {
        this.y().set(y);
    }
    /**
     * Called whenever another entity enters the same square as this entity.
     * @param other the other entity that has just been imposed on this
     * entity's square.
     */
    public void onEntityEnter(Entity other) {
        System.out.println(other.getTag() + " entered " + this.getTag());
    }
}
