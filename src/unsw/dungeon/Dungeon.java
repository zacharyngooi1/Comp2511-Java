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
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
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
     * Find the entities at square (x, y).
     * @return a list of entities.
     */
    public List<Entity> getEntitiesAtSquare(int x, int y) {
        List<Entity> list = new ArrayList<Entity>();

        for (Entity entity : entities) {
            if (entity != null && entity.getX() == x && entity.getY() == y) {
                list.add(entity);
            }
        }

        return list;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}
