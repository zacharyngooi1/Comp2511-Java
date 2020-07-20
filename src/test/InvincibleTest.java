package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.json.JSONArray;

import unsw.dungeon.*;
import unsw.dungeon.Entity.Tag;

class InvincibleTest {

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
                .put(JSONFactory.entity("invincibility", 2, 1))
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
        assertEquals(dungeon.getEntitiesAtSquare(2, 1).get(0).getClass(), Invincibility.class);
    }

    @Test
    void TestExistence() {
        assertEquals(dungeon.getEntitiesAtSquare(1, 1).size(), 1);
        assertEquals(dungeon.getEntitiesAtSquare(2, 1).size(), 1);
        assertEquals(dungeon.getEntitiesAtSquare(4, 1).size(), 1);
    }

    @Test
    void TestRunAway() {
        Double diff;
        Double compare;
        boolean check = false;
        // Get initial position
        diff = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());

        player.moveRight();
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        if (compare <= diff) {
            check = true;
        }
        assertTrue(check);
        assertTrue(player.hasConsumable(Tag.INVINCIBILITY));
        check = false;

        // Assert invincibility value
        Invincibility currentInvincibility = player.getInvincibility();
        assertEquals(currentInvincibility.getValue(),19);

        // Get enemy new position
        diff = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());

        if (compare >= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;

        player.moveRight();
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());

        if (compare >= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;

        player.moveRight();
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());

        if (compare >= diff) {
            check = true;
        }
        assertTrue(check);
    }

    @Test
    void TestMultipleRun() {
        Double diff;
        Double compare;
        Enemy enemy2 = new Enemy(dungeon, 6, 1);
        dungeon.addEntity(enemy2);
        boolean check = false;
        // Get initial position
        diff = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        // Take potion
        player.moveRight();
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        if (compare <= diff) {
            check = true;
        }
        assertTrue(check);
        assertTrue(player.hasConsumable(Tag.INVINCIBILITY));

        check = false;
        // Assert invincibility value
        Invincibility currentInvincibility = player.getInvincibility();
        assertEquals(currentInvincibility.getValue(),19);
        diff = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());

        player.moveRight();
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        if (compare >= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;
        compare = enemy2.calDis(player.getX(), player.getY(), enemy2.getX(), enemy2.getY());
        if (compare >= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;

        player.moveDown();
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        if (compare >= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;
        compare = enemy2.calDis(player.getX(), player.getY(), enemy2.getX(), enemy2.getY());
        if (compare >= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;

        player.moveRight();
        compare = enemy.calDis(player.getX(), player.getY(), enemy.getX(), enemy.getY());
        if (compare >= diff) {
            check = true;
        }
        assertTrue(check);
        check = false;
        compare = enemy2.calDis(player.getX(), player.getY(), enemy2.getX(), enemy2.getY());
        if (compare >= diff) {
            check = true;
        }
        assertTrue(check);
    }

    @Test
    void TestPotionKill() {

        // box player and enemy in
        Wall wall1 = new Wall(dungeon, 1,0);
        Wall wall2 = new Wall(dungeon, 2,0);
        Wall wall3 = new Wall(dungeon, 3,0);
        Wall wall4 = new Wall(dungeon, 4,0);

        Wall wall5 = new Wall(dungeon, 5,1);

        Wall wall6 = new Wall(dungeon, 1,2);
        Wall wall7 = new Wall(dungeon, 2,2);
        Wall wall8 = new Wall(dungeon, 3,2);
        Wall wall9 = new Wall(dungeon, 4,2);
        dungeon.addEntity(wall1);
        dungeon.addEntity(wall2);
        dungeon.addEntity(wall3);
        dungeon.addEntity(wall4);
        dungeon.addEntity(wall5);
        dungeon.addEntity(wall6);
        dungeon.addEntity(wall7);
        dungeon.addEntity(wall8);
        dungeon.addEntity(wall9);

        // Player is now boxed in with enemy
        player.moveRight();
        assertTrue(player.hasConsumable(Tag.INVINCIBILITY));

        // assert invincibility value
        Invincibility currentInvincibility = player.getInvincibility();
        assertEquals(currentInvincibility.getValue(),19);
        
        // Enemy will only be able to run one square away
        player.moveRight();
        player.moveRight();

        // enemy should now be dead
        assertEquals(dungeon.getEntitiesAtSquare(enemy.getX(), enemy.getY()).size() , 1);

        // Check that the entity is an enemy
        assertEquals(dungeon.getEntitiesAtSquare(enemy.getX(), enemy.getY()).get(0).getTag(), Tag.PLAYER);

        // Ensure the player has been killed
        assertEquals(dungeon.getEntitiesAtSquare(player.getX(), player.getY()).get(0).getTag(), Tag.PLAYER);
        assertEquals(dungeon.getEnemies().size(), 0);
    }

    @Test
    void TestPotionRefill() { 
        Invincibility potion = new Invincibility(dungeon, 2, 3);
        dungeon.addEntity(potion);

        player.moveRight();
        assertTrue(player.hasConsumable(Tag.INVINCIBILITY));

        // Assert invincibility value
        Invincibility currentInvincibility = player.getInvincibility();
        assertEquals(currentInvincibility.getValue(),19);

        player.moveDown();
        assertTrue(player.hasConsumable(Tag.INVINCIBILITY));

        // Assert invincibility value
        currentInvincibility = player.getInvincibility();
        assertEquals(currentInvincibility.getValue(),18);

        // Player will move to another potion and refill his potion value
        player.moveDown();
        assertTrue(player.hasConsumable(Tag.INVINCIBILITY));

        // Assert invincibility value
        currentInvincibility = player.getInvincibility();
        assertEquals(currentInvincibility.getValue(),19);

        // Have player completely reduce count of invincibility
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();

        // Check player's invincibility has run out.
        assertFalse(player.hasConsumable(Tag.INVINCIBILITY));
    }
}