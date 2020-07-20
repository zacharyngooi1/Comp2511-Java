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

public class FloorSwitchTest {
    private Dungeon dungeon;
    private Player player;
    private FloorSwitch floorSwitch;

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
        player.moveTo(1, 0);
        player.moveDown();
        assertEquals(floorSwitch.getStatus(), true);
    }

    @Test
    void toggleable() {
        player.moveTo(1, 0);
        player.moveDown();
        assertEquals(floorSwitch.getStatus(), true);

        player.moveDown();
        assertEquals(floorSwitch.getStatus(), false);

        player.moveTo(player.getX(), player.getY() + 2);
        player.moveUp();
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

        player = dungeon.getPlayer();
        floorSwitch = new FloorSwitch(dungeon, 1, 2);
        dungeon.addEntity(floorSwitch);

        // Add the door manually so it can be easily set to true.
        Door door = new Door(dungeon, 1, 2, 1);
        door.setStatus(true);

        player.moveTo(1, 0);
        player.moveDown();
        assertEquals(floorSwitch.getStatus(), true);
    }
}
