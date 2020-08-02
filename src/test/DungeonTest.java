package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.json.JSONArray;

import java.util.Random;

import model.Dungeon;
import model.Entity;

public class DungeonTest {
    private Random random = new Random();

    @Test
    public void correctPlayerStorage() {
        Dungeon dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 0, 0)),
            JSONFactory.goal("treasure")
        ));

        // Assert that the player is in the designated location.
        Entity entity = dungeon.getEntitiesAtSquare(0, 0).get(0);

        // Assert the dungeon's bookkeeping is correct.
        assertEquals(dungeon.getPlayer(), entity);
        dungeon.removeEntity(entity);
        assertEquals(dungeon.getPlayer(), null);
    }

    @ParameterizedTest
    @ValueSource(strings = {"wall", "exit", "treasure", "door",
        "key", "boulder", "switch", "portal", "enemy", "sword",
        "invincibility"})
    public void correctEntityStorage(String type) {
        int dungeonWidth = random.nextInt(20) + 1;
        int dungeonHeight = random.nextInt(20) + 1;
        int x = random.nextInt(dungeonWidth);
        int y = random.nextInt(dungeonHeight);

        Dungeon dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            dungeonWidth,
            dungeonHeight,
            new JSONArray()
                // A player is necessary for the enemy entity to be created.
                // It is placed away from the dungeon as to not intefere
                // with the getEntitiesAtSquare() scan below.
                .put(JSONFactory.entity("player", -1, -1))
                .put(JSONFactory.entity(type, x, y, 1)),
            JSONFactory.goal("treasure")
        ));

        assertEquals(dungeon.getWidth(), dungeonWidth);
        assertEquals(dungeon.getHeight(), dungeonHeight);

        // Assert all squares in which the entity wasn't spawned are empty.
        for (int i = 0; i < dungeonWidth; i++) {
            for (int j = 0; j < dungeonHeight; j++) {
                if (i != x && j != y) {
                    assertEquals(dungeon.getEntitiesAtSquare(i, j).size(), 0);
                }
            }
        }

        // Assert exactly one entity was spawned.
        assertEquals(dungeon.getEntitiesAtSquare(x, y).size(), 1);
        Entity entity = dungeon.getEntitiesAtSquare(x, y).get(0);

        // Assert the dungeon's bookkeeping is correct.
        if (type.equals("portal")) {
            assertEquals(dungeon.getPortals().size(), 1);
            assertEquals(dungeon.getPortals().get(0), entity);
        } else {
            assertEquals(dungeon.getPortals().size(), 0);
        }

        if (type.equals("treasure")) {
            assertEquals(dungeon.getTreasures().size(), 1);
            assertEquals(dungeon.getTreasures().get(0), entity);
        } else {
            assertEquals(dungeon.getTreasures().size(), 0);
        }

        if (type.equals("enemy")) {
            assertEquals(dungeon.getEnemies().size(), 1);
            assertEquals(dungeon.getEnemies().get(0), entity);
        } else {
            assertEquals(dungeon.getEnemies().size(), 0);
        }

        if (type.equals("switch")) {
            assertEquals(dungeon.getFloorSwitches().size(), 1);
            assertEquals(dungeon.getFloorSwitches().get(0), entity);
        } else {
            assertEquals(dungeon.getFloorSwitches().size(), 0);
        }

        if (type.equals("exit")) {
            assertEquals(dungeon.getExit(), entity);
        } else {
            assertEquals(dungeon.getExit(), null);
        }

        dungeon.removeEntity(entity);
        assertEquals(dungeon.getPortals().size(), 0);
        assertEquals(dungeon.getTreasures().size(), 0);
        assertEquals(dungeon.getEnemies().size(), 0);
        assertEquals(dungeon.getFloorSwitches().size(), 0);
        assertEquals(dungeon.getExit(), null);
    }
}
