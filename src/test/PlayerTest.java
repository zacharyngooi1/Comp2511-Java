package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.json.JSONArray;

import unsw.dungeon.*;

class PlayerTest {

    private Dungeon dungeon;
    private Player player;

	@BeforeEach
	void initializeTest() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 1, 1)),
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
    void TestExistece() {
        assertEquals(dungeon.getEntitiesAtSquare(1, 1).size(), 1);
    }
    

    @Test
	void movingPlayer() {
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);

		player.moveLeft();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);

		player.moveRight();
		assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);
        
        player.moveUp();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);

		player.moveDown();
		assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);
        
        player.moveDown();
		assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 2);
        
        player.moveDown();
		assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 3);
        
        player.moveRight();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 3);
    }
    
    @Test
	void collisionTest() {
		// Player at (1,1)
        Door door = new Door(dungeon, 2, 1, 0);
        dungeon.addEntity(door);
        assertEquals(dungeon.getEntitiesAtSquare(2, 1).size(), 1);

        player.moveTo(2, 1);
        // Player should not be able to move to (2,1)
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
    }
    
    @Test
	void testBoundary() {
		assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);
        
        player.moveUp();
        assertEquals(player.getY(), 0);
        player.moveUp();
        assertEquals(player.getY(), 0);
        player.moveLeft();
        assertEquals(player.getX(), 0);
        player.moveLeft();
        assertEquals(player.getX(), 0);

    }

    @Test
	void testBoundaryComprehensive() {
		assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);

        player.moveTo(0, 0);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        // Moving to all 4 out of bounds corners of the map.

        // Top left
        player.moveTo(-1,0);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        player.moveTo(0,-1);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        player.moveTo(-1,-1);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        // Top right
        player.moveTo(9,-1);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        player.moveTo(10,0);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        player.moveTo(10,-1);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        // Bottom right
        player.moveTo(10,9);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        player.moveTo(9,10);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        player.moveTo(10,10);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        // Bottom left
        player.moveTo(-1,9);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        player.moveTo(0,10);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
        player.moveTo(-1,10);
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        for (int x= 0; x < 50; x ++) {
            player.moveUp();
        }
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        for (int x= 0; x < 50; x ++) {
            player.moveRight();
        }
        assertEquals(player.getX(), 9);
        assertEquals(player.getY(), 0);

        for (int x= 0; x < 50; x ++) {
            player.moveDown();
        }
        assertEquals(player.getX(), 9);
        assertEquals(player.getY(), 9);

        for (int x= 0; x < 50; x ++) {
            player.moveLeft();
        }
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 9);

        for (int x= 0; x < 50; x ++) {
            player.moveUp();
        }
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        assertEquals(dungeon.getEntitiesAtSquare(0, 0).size(), 1);
    }
}
