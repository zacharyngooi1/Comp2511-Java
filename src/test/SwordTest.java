package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.json.JSONArray;

import unsw.dungeon.*;
import unsw.dungeon.Entity.Tag;

class SwordTest {

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
                .put(JSONFactory.entity("enemy", 4, 1))
                .put(JSONFactory.entity("sword", 2, 1))
                .put(JSONFactory.entity("exit", 9, 9)),
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
        assertEquals(dungeon.getEntitiesAtSquare(2, 1).get(0).getClass(), Sword.class);
    }


    @Test
    void TestExistence() {
        assertEquals(dungeon.getEntitiesAtSquare(1, 1).size(), 1);
        assertEquals(dungeon.getEntitiesAtSquare(2, 1).size(), 1);
        assertEquals(dungeon.getEntitiesAtSquare(4, 1).size(), 1);
    }

    @Test
    void TestSimpleKill() {
        // pick up sword
        player.moveRight();
        // assert player has sword
        assertTrue(player.hasConsumable(Tag.SWORD));
        // assert sword value
        Sword currentSword = player.getSword();
        assertEquals(currentSword.getValue(), 5);

        assertEquals(dungeon.getEntitiesAtSquare(enemy.getX(), enemy.getY()).get(0).getTag(), Tag.ENEMY);

        // Move player to enemy
        player.moveRight();

        assertEquals(dungeon.getEntitiesAtSquare(player.getX(), player.getY()).size(), 1);

        assertEquals(dungeon.getEntitiesAtSquare(player.getX(), player.getY()).get(0).getTag(), Tag.PLAYER);

        assertEquals(dungeon.getEntitiesAtSquare(enemy.getX(), enemy.getY()).get(0).getTag(), Tag.PLAYER);

        // Check value of sword
        assertEquals(currentSword.getValue(), 4);
    }

    @Test
    void TestMultipleKill() {
        // Create second enemy
        Enemy enemy2 = new Enemy(dungeon, 6, 1);
        dungeon.addEntity(enemy2);

        // Pick up sword
        player.moveRight();

        // Assert player has sword
        assertTrue(player.hasConsumable(Tag.SWORD));

        // Assert sword value
        Sword currentSword = player.getSword();
        assertEquals(currentSword.getValue(), 5);

        // Check existence of enemy
        assertEquals(dungeon.getEntitiesAtSquare(enemy.getX(), enemy.getY()).get(0).getTag(), Tag.ENEMY);
        assertEquals(dungeon.getEntitiesAtSquare(enemy2.getX(), enemy2.getY()).get(0).getTag(), Tag.ENEMY);

        // Move player to enemy 1
        player.moveRight();      
        
        assertEquals(dungeon.getEntitiesAtSquare(player.getX(), player.getY()).size(), 1);

        assertEquals(dungeon.getEntitiesAtSquare(player.getX(), player.getY()).get(0).getTag(), Tag.PLAYER);

        assertEquals(dungeon.getEntitiesAtSquare(enemy.getX(), enemy.getY()).get(0).getTag(), Tag.PLAYER);

        assertEquals(currentSword.getValue(), 4);

        player.moveRight();      
        
        assertEquals(dungeon.getEntitiesAtSquare(player.getX(), player.getY()).size(), 1);

        assertEquals(dungeon.getEntitiesAtSquare(player.getX(), player.getY()).get(0).getTag(), Tag.PLAYER);

        assertEquals(dungeon.getEntitiesAtSquare(enemy2.getX(), enemy2.getY()).get(0).getTag(), Tag.PLAYER);

        assertEquals(currentSword.getValue(), 3);
    }   

    @Test
    void TestSwordValue() {
        // Create second enemy
        Enemy enemy2 = new Enemy(dungeon, 6, 1);
        Enemy enemy3 = new Enemy(dungeon, 7, 1);
        Enemy enemy4 = new Enemy(dungeon, 8, 1);
        Enemy enemy5 = new Enemy(dungeon, 9, 1);
        dungeon.addEntity(enemy2);
        dungeon.addEntity(enemy3);
        dungeon.addEntity(enemy4);
        dungeon.addEntity(enemy5);

        // Pick up sword
        player.moveRight();

        // Assert player has sword
        assertTrue(player.hasConsumable(Tag.SWORD));

        // Assert sword value
        Sword currentSword = player.getSword();
        assertEquals(currentSword.getValue(), 5);
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        assertEquals(currentSword.getValue(), 0);
        assertFalse(player.hasConsumable(Tag.SWORD));

        // Make a new sword
        Sword newSword = new Sword(dungeon, player.getX() - 1, player.getY());
        dungeon.addEntity(newSword);

        // Place sword on the left of player
        player.moveLeft();
        assertTrue(player.hasConsumable(Tag.SWORD));
        assertEquals(newSword.getValue(), 5);
        Enemy enemy6 = new Enemy(dungeon, player.getX() -1, player.getY());
        dungeon.addEntity(enemy6);
        player.moveLeft();
        assertEquals(newSword.getValue(), 4);
        Sword refill = new Sword(dungeon, player.getX() - 1, player.getY());
        dungeon.addEntity(refill);
        player.moveLeft();
        assertEquals(newSword.getValue(), 5);
    }

}
