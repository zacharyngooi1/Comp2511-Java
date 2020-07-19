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
    private Goal goal;
    private Player player;
    private Exit exit;
    private List<Entity> entities;
    private List<Portal> portals;
    private List<Treasure> treasures;
    private List<Enemy> enemies;
    private List<FloorSwitch> floorSwitches;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.goal = null;
        this.player = null;
        this.exit = null;
        this.entities = new ArrayList<Entity>();
        this.portals = new ArrayList<Portal>();
        this.treasures = new ArrayList<Treasure>();
        this.enemies = new ArrayList<Enemy>();
        this.floorSwitches = new ArrayList<FloorSwitch>();
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

    public Exit getExit() {
        return exit;
    }

    public List<Portal> getPortals() {
        return portals;
    }

    public List<Treasure> getTreasures() {
        return treasures;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<FloorSwitch> getFloorSwitches() {
        return floorSwitches;
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

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public boolean isGameWon() {
        return goal.isComplete();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);

        switch (entity.getTag()) {
            case PLAYER:
                this.player = (Player) entity;
                break;
            case EXIT:
                this.exit = (Exit) entity;
                break;
            case PORTAL:
                portals.add((Portal) entity);
                break;
            case TREASURE:
                treasures.add((Treasure) entity);
                break;
            case ENEMY:
                enemies.add((Enemy) entity);
                break;
            case FLOORSWITCH:
                floorSwitches.add((FloorSwitch) entity);
                break;
            default:
                break;
        }
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);

        switch (entity.getTag()) {
            case PLAYER:
                System.out.println("Restart level");
                break;
            case PORTAL:
                portals.remove(entity);
                break;
            case TREASURE:
                treasures.remove(entity);
                System.out.println(treasures.size() + " treasure(s) remaining");
                break;
            case ENEMY:
                enemies.remove(entity);
                break;
            case FLOORSWITCH:
                floorSwitches.remove(entity);
                break;
            default:
                break;
        }
    }
}
