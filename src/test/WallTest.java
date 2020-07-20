package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.json.JSONArray;

import unsw.dungeon.*;

class WallTest {

    private Dungeon dungeon;
    private Player player;
    private Enemy enemy;

	@BeforeEach
	void initializeTest() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 1, 1))
                .put(JSONFactory.entity("enemy",5, 5))
                .put(JSONFactory.entity("wall", 0, 0))
                .put(JSONFactory.entity("wall", 1, 0))
                .put(JSONFactory.entity("wall", 2, 0))
                .put(JSONFactory.entity("wall", 2, 1))
                .put(JSONFactory.entity("wall", 2, 2))
                .put(JSONFactory.entity("wall", 1, 2))
                .put(JSONFactory.entity("wall", 0, 2))
                .put(JSONFactory.entity("wall", 0, 1))
                .put(JSONFactory.entity("wall", 4, 4))
                .put(JSONFactory.entity("wall", 5, 4))
                .put(JSONFactory.entity("wall", 6, 4))
                .put(JSONFactory.entity("wall", 6, 5))
                .put(JSONFactory.entity("wall", 6, 6))
                .put(JSONFactory.entity("wall", 5, 6))
                .put(JSONFactory.entity("wall", 4, 6))
                .put(JSONFactory.entity("wall", 4, 5)),
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
        assertEquals(dungeon.getEnemies().size(), 1);
        enemy = dungeon.getEnemies().get(0);
    }


    @Test
    void TestMovement() {
        // Enemy is encased and thus cannot move either even though enemy is trying to move to player.
        player.moveDown();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);   
        assertEquals(enemy.getX(), 5);
        assertEquals(enemy.getY(), 5);     
        player.moveLeft();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 5);
        assertEquals(enemy.getY(), 5);
        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 5);
        assertEquals(enemy.getY(), 5);
        player.moveUp();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);
        assertEquals(enemy.getX(), 5);
        assertEquals(enemy.getY(), 5);
    }

    @Test
    void TestBoulderWall() {
        // Remove wall to the right of player
        dungeon.removeEntity(dungeon.getEntitiesAtSquare(2, 1).get(0));
        // Add boulder
        Boulder boulder = new Boulder(dungeon, 2, 1);
        dungeon.addEntity(boulder);
        // add wall to the right of boulder
        dungeon.addEntity(new Wall(dungeon, 3, 1));
        // Attempt to move player to the right to move boulder at the same time but should fail as wall is blocking boulder
        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);
        assertEquals(boulder.getX(),2);
        assertEquals(boulder.getY(), 1);
        // Enemy should not move either as player is unable to move
        assertEquals(enemy.getX(), 5);
        assertEquals(enemy.getY(), 5);
    }
}