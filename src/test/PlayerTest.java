package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;

class PlayerTest {

	private Dungeon dungeon;
	private Player player;

	@BeforeEach
	void initialize() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
	}

	@Test
	void checkVisible() {
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
	}
}