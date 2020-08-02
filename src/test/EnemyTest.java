package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.json.JSONArray;

import model.*;
import model.Entity.Tag;

class EnemyTest {

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
                .put(JSONFactory.entity("enemy", 3, 1)),
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
    void TestExistence() {
        assertEquals(dungeon.getEntitiesAtSquare(1, 1).size(), 1);
        assertEquals(dungeon.getEntitiesAtSquare(3, 1).size(), 1);
    }

    @Test
    void TestComprehensiveChase() {
        enemy.moveTo(9, 9);
        // Calc distance between player and enemy
        Double diff = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        Double compare;
        boolean check = false;
        player.moveDown();
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        if (compare <= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;

        player.moveRight();
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        if (compare <= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;

        player.moveRight();
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        if (compare <= diff) {
            check = true;
        }
        assertTrue(check);
    }


    @Test
    void TestMulipleChase() {
        enemy.moveTo(9, 9);
        Enemy enemy2 = new Enemy(dungeon, 0, 9);
        dungeon.addEntity(enemy2);

        Double diff = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        Double compare;
        boolean check = false;

        player.moveDown();
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        if (compare <= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;
        compare = enemy2.calDis(player.getX(), player.getY(), enemy2.getX(), enemy2.getY());
        if (compare <= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;

        player.moveRight();
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        if (compare <= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;
        compare = enemy2.calDis(player.getX(), player.getY(), enemy2.getX(), enemy2.getY());
        if (compare <= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;

        player.moveRight();
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        if (compare <= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;
        compare = enemy2.calDis(player.getX(), player.getY(), enemy2.getX(), enemy2.getY());
        if (compare <= diff) {
            check = true;
        }
        assertTrue(check);
    }

    @Test
    void TestSimpleKill() {
        player.moveRight();
        assertEquals(dungeon.getEntitiesAtSquare(2, 1).size(), 1);
    }

    @Test
    void TestComprehensiveKill() {
        // Check existence of player
        assertEquals(dungeon.getEntitiesAtSquare(player.getX(), player.getY()).size(), 1);
        assertEquals(dungeon.getEntitiesAtSquare(player.getX(), player.getY()).get(0).getTag(), Tag.PLAYER);

        // move player to square next to enemy
        // Enemy should automatically go to the same square.
        player.moveRight();

        // Check the number of entities at the enemy square
        assertEquals(dungeon.getEntitiesAtSquare(enemy.getX(), enemy.getY()).size() , 1);
        // Check that the entity is an enemy
        assertEquals(dungeon.getEntitiesAtSquare(enemy.getX(), enemy.getY()).get(0).getTag(), Tag.ENEMY);
        // Ensure the player has been killed
        assertEquals(dungeon.getEntitiesAtSquare(player.getX(), player.getY()).get(0).getTag(), Tag.ENEMY);
    }
}