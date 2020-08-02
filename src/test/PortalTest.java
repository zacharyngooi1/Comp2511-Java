package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.json.JSONArray;

import model.*;

public class PortalTest {
    private Dungeon dungeon;
    private Player player;
    private Boulder boulder;
    private Enemy enemy;

    @BeforeEach
    void initialize() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 0, 0))
                .put(JSONFactory.entity("boulder", 4, 4))
                .put(JSONFactory.entity("enemy", 5, 5))
                .put(JSONFactory.entity("portal", 2, 1, 0))
                .put(JSONFactory.entity("portal", 2, 2, 0))
                .put(JSONFactory.entity("portal", 3, 1, 1))
                .put(JSONFactory.entity("portal", 3, 2, 1)),
            JSONFactory.goal("treasure")
        ));

        player = dungeon.getPlayer();
        boulder = (Boulder) dungeon.getEntitiesAtSquare(4, 4).get(0);
        enemy = (Enemy) dungeon.getEntitiesAtSquare(5, 5).get(0);
    }

    @Test
    void teleportsPlayer() {
        player.moveTo(2, 1);
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);
        player.moveTo(2, 2);
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
    }

    @Test
    void teleportsEnemy() {
        enemy.moveTo(2, 1);
        assertEquals(enemy.getX(), 2);
        assertEquals(enemy.getY(), 2);
        enemy.moveTo(2, 2);
        assertEquals(enemy.getX(), 2);
        assertEquals(enemy.getY(), 1);
    }

    @Test
    void teleportsBoulder() {
        boulder.moveTo(2, 1);
        assertEquals(boulder.getX(), 2);
        assertEquals(boulder.getY(), 2);
        boulder.moveTo(2, 2);
        assertEquals(boulder.getX(), 2);
        assertEquals(boulder.getY(), 1);
    }

    @Test
    void blockedPortal() {
        boulder.moveTo(2, 1);
        assertEquals(boulder.getX(), 2);
        assertEquals(boulder.getY(), 2);

        // Try to enter the portal the boulder entered, but fail because the
        // boulder is blocking the other end.
        player.moveTo(2, 1);
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
    }

    @Test
    void simultaneousTeleport() {
        player.moveTo(2, 0);
        enemy.moveTo(2, 3);

        // Move down into the portal. The enemy's AI will cause it to move up
        // into the corresponding portal and the two entities should swap.
        player.moveDown();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);
        assertEquals(enemy.getX(), 2);
        assertEquals(enemy.getY(), 1);
    }

    @Test
    void twoPortalPairs() {
        player.moveTo(2, 1);
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);
        player.moveTo(2, 2);
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);

        player.moveTo(3, 1);
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 2);
        player.moveTo(3, 2);
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 1);
    }

    /**
     * Successfully interacts with the portal despite interacting with
     * as many other things as possible on the same square.
     */
    @Test
    void busySquare() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 0, 0))
                .put(JSONFactory.entity("sword", 0, 0))

                .put(JSONFactory.entity("portal", 1, 1, 0))

                // Box in the second portal so the enemy won't be able to
                // leave.
                .put(JSONFactory.entity("portal", 2, 2, 0))
                .put(JSONFactory.entity("wall", 1, 2, 0))
                .put(JSONFactory.entity("wall", 3, 2, 0))
                .put(JSONFactory.entity("wall", 2, 1, 0))
                .put(JSONFactory.entity("wall", 2, 3, 0))

                .put(JSONFactory.entity("enemy", 1, 1))
                .put(JSONFactory.entity("exit", 2, 2))
                .put(JSONFactory.entity("key", 2, 2, 1))
                .put(JSONFactory.entity("treasure", 2, 2))
                .put(JSONFactory.entity("switch", 2, 2))
                .put(JSONFactory.entity("sword", 2, 2))
                .put(JSONFactory.entity("invincibility", 2, 2)),
            JSONFactory.goal("treasure")
        ));

        player = dungeon.getPlayer();

        // Pickup a sword to kill the enemy upon entering the square.
        player.moveTo(0, 0);

        // Add the door manually so it can easily be opened. Teleporting
        // the player with a key into a door will not work because the portal
        // will see the exit as blocked.
        Door door = new Door(dungeon, 2, 2, 1);
        door.setCollisionLayer(new CollisionLayer(0));
        dungeon.addEntity(door);

        player.moveTo(1, 1);
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);
    }
}
