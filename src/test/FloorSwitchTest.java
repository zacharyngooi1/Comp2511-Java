package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.json.JSONArray;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Enemy;
import unsw.dungeon.Door;
import unsw.dungeon.Boulder;
import unsw.dungeon.CollisionLayer;

public class FloorSwitchTest {
    private Dungeon dungeon;
    private Player player;
    private FloorSwitch floorSwitch;
    private Boulder boulder;

    @BeforeEach
    void initialize() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 0, 0))
                .put(JSONFactory.entity("boulder", 1, 1))
                .put(JSONFactory.entity("switch", 1, 2)),
            JSONFactory.goal("treasure")
        ));

        player = dungeon.getPlayer();
        floorSwitch = (FloorSwitch) dungeon.getEntitiesAtSquare(1, 2).get(0);
        boulder = (Boulder) dungeon.getEntitiesAtSquare(1, 1).get(0);
    }

    @Test
    void startInactive() {
        assertEquals(floorSwitch.getStatus(), false);
    }

    @Test
    void notActivatedByPlayer() {
        player.moveTo(1, 2);
        assertEquals(floorSwitch.getStatus(), false);
    }

    @Test
    void notActivatedByEnemy() {
        Enemy enemy = new Enemy(dungeon, 1, 2);
        dungeon.addEntity(enemy);
        assertEquals(floorSwitch.getStatus(), false);
    }

    @Test
    void activatedByBoulder() {
        boulder.moveTo(1, 2);
        assertEquals(floorSwitch.getStatus(), true);
    }

    @Test
    void toggleable() {
        boulder.moveTo(1, 2);
        assertEquals(floorSwitch.getStatus(), true);

        boulder.moveTo(2, 2);
        assertEquals(floorSwitch.getStatus(), false);

        boulder.moveTo(1, 2);
        assertEquals(floorSwitch.getStatus(), true);
    }

    /**
     * Successfully interacts with the switch despite interacting with
     * as many other things as possible on the same square.
     */
    @Test
    void busySquare() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 0, 0))
                .put(JSONFactory.entity("boulder", 1, 1))

                .put(JSONFactory.entity("exit", 1, 2))
                .put(JSONFactory.entity("key", 1, 2, 1))
                .put(JSONFactory.entity("treasure", 1, 2))
                .put(JSONFactory.entity("sword", 1, 2))
                .put(JSONFactory.entity("invincibility", 1, 2))
                .put(JSONFactory.entity("portal", 1, 2, 1))
                .put(JSONFactory.entity("portal", 1, 2, 1)),
            JSONFactory.goal("treasure")
        ));

        floorSwitch = new FloorSwitch(dungeon, 1, 2);
        boulder = (Boulder) dungeon.getEntitiesAtSquare(1, 1).get(0);
        dungeon.addEntity(floorSwitch);

        // Add the door manually so it can easily be opened.
        Door door = new Door(dungeon, 1, 2, 1);
        door.setCollisionLayer(new CollisionLayer(0));
        dungeon.addEntity(door);

        boulder.moveTo(1, 2);
        assertEquals(floorSwitch.getStatus(), true);
    }
}
