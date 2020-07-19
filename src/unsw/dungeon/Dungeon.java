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
    private List<Treasure> treasures;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<Entity>();
        this.portals = new ArrayList<Portal>();
        this.treasures = new ArrayList<Treasure>();
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

    public List<Portal> getPortals() {
        return portals;
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

    public void addPortal(Portal portal) {
        portals.add(portal);
    }

    public void addTreasure(Treasure treasure) {
        treasures.add(treasure);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);

        switch (entity.getTag()) {
            case PORTAL:
                portals.remove(entity);
                break;
            case TREASURE:
                treasures.remove(entity);
                System.out.println(treasures.size() + " treasure(s) remaining");
                break;
            default:
                break;
        }
    }
}
