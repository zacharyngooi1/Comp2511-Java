package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import org.json.JSONArray;

import model.*;
import model.Entity.Tag;

class DoorTest {

    private Dungeon dungeon;
    private Player player;

    @Test
    void SimpleDoorTest() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 1, 1))
                .put(JSONFactory.entity("door", 3, 1, 0)),
                JSONFactory.goal(
                "AND",
                new JSONArray()
                    .put(JSONFactory.goal("enemies"))
                    .put(JSONFactory.goal("boulders"))
                    .put(JSONFactory.goal("treasure"))
                    .put(JSONFactory.goal("exit"))
            )
        ));
        player = dungeon.getPlayer();

        // move player to the left of door
        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);

        // Player blocked by door to the right
        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);

        // move player to below door
        player.moveDown();
        player.moveRight();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 2);

        // Player blocked by door above him
        player.moveUp();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 2);

        // move player to the right of door
        player.moveRight();
        player.moveUp();
        assertEquals(player.getX(), 4);
        assertEquals(player.getY(), 1);

        // Player blocked by door to the left of himself
        player.moveLeft();
        assertEquals(player.getX(), 4);
        assertEquals(player.getY(), 1);

        // move player to above door
        player.moveUp();
        player.moveLeft();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 0);

        // Player blocked by door below him
        player.moveDown();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 0);
    }


    @Test
    void DoorUnlockTest() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 1, 1))
                .put(JSONFactory.entity("door", 3, 1, 0))
                .put(JSONFactory.entity("key", 2, 1, 0)),
                JSONFactory.goal(
                "AND",
                new JSONArray()
                    .put(JSONFactory.goal("enemies"))
                    .put(JSONFactory.goal("boulders"))
                    .put(JSONFactory.goal("treasure"))
                    .put(JSONFactory.goal("exit"))
            )
        ));
        player = dungeon.getPlayer();
        player.moveRight();

        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
        assertEquals(player.getKeys().size(),1);

        // Unlock door
        player.moveRight();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 1);
        assertEquals(player.getKeys().size(),0);
        assertEquals(dungeon.getEntitiesAtSquare(3, 1).size(), 2);

    }

    @Test
    void DoorUnlockFailTest() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 1, 1))
                .put(JSONFactory.entity("door", 3, 1, 0))
                .put(JSONFactory.entity("key", 2, 1, 1)),
                JSONFactory.goal(
                "AND",
                new JSONArray()
                    .put(JSONFactory.goal("enemies"))
                    .put(JSONFactory.goal("boulders"))
                    .put(JSONFactory.goal("treasure"))
                    .put(JSONFactory.goal("exit"))
            )
        ));
        player = dungeon.getPlayer();
        player.moveRight();

        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
        assertEquals(player.getKeys().size(),1);

        // Fail to Unlock door
        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
        assertEquals(player.getKeys().size(),1);
        assertEquals(dungeon.getEntitiesAtSquare(3, 1).size(), 1);
        assertEquals(dungeon.getEntitiesAtSquare(3, 1).get(0).getTag(), Tag.DOOR);
    }

    @Test
    void DoorUnlockFailTest2() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 1, 1))
                .put(JSONFactory.entity("door", 3, 1, 0))
                .put(JSONFactory.entity("key", 2, 1, 1))
                .put(JSONFactory.entity("key", 0, 1, 2)),
                JSONFactory.goal(
                "AND",
                new JSONArray()
                    .put(JSONFactory.goal("enemies"))
                    .put(JSONFactory.goal("boulders"))
                    .put(JSONFactory.goal("treasure"))
                    .put(JSONFactory.goal("exit"))
            )
        ));
        player = dungeon.getPlayer();
        player.moveLeft();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 1);
        assertEquals(player.getKeys().size(),1);

        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);

        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
        assertEquals(player.getKeys().size(),2);

        // Fail to Unlock door even with 2 keys
        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
        assertEquals(player.getKeys().size(),2);
        assertEquals(dungeon.getEntitiesAtSquare(3, 1).size(), 1);
        assertEquals(dungeon.getEntitiesAtSquare(3, 1).get(0).getTag(), Tag.DOOR);
    }
}