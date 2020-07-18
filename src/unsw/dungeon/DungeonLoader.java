package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads the model (the dungeon, from a .json file) but not the view.
 * 
 * It is the responsibility of a subclass to implement the functions that
 * take the model's entities made by this class, create corresponding view/UI
 * elements and then tie the two together.
 * 
 * @author Robert Clifton-Everest
 */
public abstract class DungeonLoader {
    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        return dungeon;
    }

    /**
     * Parses the JSON to create an entity.
     * @return
     */
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;

        switch (type) {
            case "player":
                Player player = new Player(dungeon, x, y);
                dungeon.setPlayer(player);
                onLoad(player);
                entity = player;
                break;
            case "wall":
                Wall wall = new Wall(dungeon, x, y);
                onLoad(wall);
                entity = wall;
                break;
            case "boulder":
                Boulder boulder = new Boulder(dungeon, x, y);
                onLoad(boulder);
                entity = boulder;
                break;
            case "enemy":
                Enemy enemy = new Enemy(dungeon, x, y);
                onLoad(enemy);
                entity = enemy;
                break;
            case "treasure":
                Treasure treasure = new Treasure(dungeon, x, y);
                onLoad(treasure);
                entity = treasure;
                break;
            case "exit":
                Exit exit = new Exit(dungeon, x, y);
                onLoad(exit);
                entity = exit;
                break;
            case "key":
                Key key = new Key(dungeon, x, y);
                onLoad(key);
                entity = key;
                break;
            case "switch":
                FloorSwitch floorSwitch = new FloorSwitch(dungeon, x, y);
                onLoad(floorSwitch);
                entity = floorSwitch;
                break;
            case "invincibility":
                Invincibility invincibility = new Invincibility(dungeon, x, y);
                onLoad(invincibility);
                entity = invincibility;
                break;
            case "door":
                Door door = new Door(dungeon, x, y);
                onLoad(door);
                entity = door;
                break;
            case "portal":
                Portal portal = new Portal(dungeon, x, y);
                onLoad(portal);
                entity = portal;
                break;
            case "sword":
                Sword sword = new Sword(dungeon, x, y);
                onLoad(sword);
                entity = sword;
                break;
            default:
                break;
        }

        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Key key);

    public abstract void onLoad(FloorSwitch floorSwitch);

    public abstract void onLoad(Invincibility floorSwitch);

    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Sword sword);
}
