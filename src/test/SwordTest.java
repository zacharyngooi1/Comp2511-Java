package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

        // Move plyer to enemy
        player.moveRight();

        assertEquals(dungeon.getEntitiesAtSquare(player.getX(), player.getY()).size(), 1);

        assertEquals(dungeon.getEntitiesAtSquare(player.getX(), player.getY()).get(0).getTag(), Tag.PLAYER);

        assertEquals(dungeon.getEntitiesAtSquare(enemy.getX(), enemy.getY()).get(0).getTag(), Tag.PLAYER);

        // Check value of sword
        assertEquals(currentSword.getValue(), 4);


    }
}
