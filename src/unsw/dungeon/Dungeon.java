/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 */
public class Dungeon {
    private int width, height;
    private List<Entity> entities;
    private List<Portal> portals;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.portals = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * Tries to find an entity at square (x, y).
     * @return the entity if it found, or otherwise null.
     */
    public Entity getEntityAtSquare(int x, int y) {
        for (Entity entity : entities) {
            if (entity != null && entity.getX() == x && entity.getY() == y) {
                return entity;
            }
        }

        return null;
    }


    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntityList() {
        return this.entities;
    }
    public List<Portal> getPortalList() {
        return this.portals;
    }
    public void addPortal(Portal p) {
        portals.add(p);
    }
}
