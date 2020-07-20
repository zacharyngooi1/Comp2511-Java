package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.json.JSONArray;

import unsw.dungeon.*;
import unsw.dungeon.Entity.Tag;

class BoulderTest {

    private Dungeon dungeon;
    private Player player;
    
    @BeforeEach
	void initializeTest() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 1, 1))
                .put(JSONFactory.entity("boulder", 2, 1)),
                JSONFactory.goal(
                "AND",
                new JSONArray()
                    .put(JSONFactory.goal("enemies"))
                    .put(JSONFactory.goal("boulders"))
                    .put(JSONFactory.goal("treasure"))
                    .put(JSONFactory.goal("exit"))
            )
        ));
        assertEquals(dungeon.getHeight(), 10);
        player = dungeon.getPlayer();
    }


    @Test
    void TestSimplePush() {
        assertEquals(dungeon.getEntitiesAtSquare(2, 1).size(), 1);

        assertEquals(dungeon.getEntitiesAtSquare(2, 1).get(0).getTag(), Tag.BOULDER);

        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);

        player.moveRight();

        assertEquals(dungeon.getEntitiesAtSquare(3, 1).size(), 1);

        assertEquals(dungeon.getEntitiesAtSquare(3, 1).get(0).getTag(), Tag.BOULDER);
    }

    @Test
    void TestMultiplePush() {
        Boulder boulder2 = new Boulder(dungeon, 4, 1);
        dungeon.addEntity(boulder2);

        assertEquals(dungeon.getEntitiesAtSquare(2, 1).size(), 1);
        assertEquals(dungeon.getEntitiesAtSquare(4, 1).size(), 1);

        assertEquals(dungeon.getEntitiesAtSquare(2, 1).get(0).getTag(), Tag.BOULDER);

        assertEquals(dungeon.getEntitiesAtSquare(4, 1).get(0).getTag(), Tag.BOULDER);

        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);

        player.moveRight();

        assertEquals(dungeon.getEntitiesAtSquare(3, 1).size(), 1);

        assertEquals(dungeon.getEntitiesAtSquare(3, 1).get(0).getTag(), Tag.BOULDER);

        assertEquals(dungeon.getEntitiesAtSquare(4, 1).size(), 1);

        assertEquals(dungeon.getEntitiesAtSquare(4, 1).get(0).getTag(), Tag.BOULDER);

        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);

        player.moveRight();

        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);

        assertEquals(dungeon.getEntitiesAtSquare(3, 1).size(), 1);

        assertEquals(dungeon.getEntitiesAtSquare(3, 1).get(0).getTag(), Tag.BOULDER);

        assertEquals(dungeon.getEntitiesAtSquare(4, 1).size(), 1);

        assertEquals(dungeon.getEntitiesAtSquare(4, 1).get(0).getTag(), Tag.BOULDER);

    }
}