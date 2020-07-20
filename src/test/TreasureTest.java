package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.json.JSONArray;

import unsw.dungeon.*;
import unsw.dungeon.Entity.Tag;

class TreasureTest {

    private Dungeon dungeon;
    private Player player;
    
    @BeforeEach
	void Initialize() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 1, 1))
                .put(JSONFactory.entity("exit", 8, 1))
                .put(JSONFactory.entity("treasure", 0, 1))
                .put(JSONFactory.entity("treasure", 2, 1))
                .put(JSONFactory.entity("treasure", 3, 1)),
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
    }

    @Test
    void SimplePick() {
        assertEquals(dungeon.getEntitiesAtSquare(2, 1).size(), 1);
        assertEquals(dungeon.getEntitiesAtSquare(2, 1).get(0).getTag(), Tag.TREASURE);

        player.moveRight();
        player.moveLeft();

        // Treasure is gone
        assertEquals(dungeon.getEntitiesAtSquare(2, 1).size(), 0);
    }

    @Test
    void TestComprehensiveGoalTreasure() {
        assertEquals(dungeon.getEntitiesAtSquare(2, 1).size(), 1);
        assertEquals(dungeon.getEntitiesAtSquare(2, 1).get(0).getTag(), Tag.TREASURE);

        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();

        assertEquals(player.getX(), 8);
        assertEquals(player.getY(), 1);

        // Player is on exit but is unable to win as one treasure still remains
        assertFalse(dungeon.checkGameWon());

        // Move player to remaining treasure
        player.moveTo(0, 1);
        player.moveTo(8, 1);
        
        assertTrue(dungeon.checkGameWon());
    }
}
