package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.json.JSONArray;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Exit;
import unsw.dungeon.Enemy;

public class ExitTest {
    private Dungeon dungeon;
    private Player player;
    private Exit exit;

    @BeforeEach
    void initialize() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 0, 0))
                .put(JSONFactory.entity("boulder", 1, 1))
                .put(JSONFactory.entity("exit", 1, 2)),
            JSONFactory.goal("treasure")
        ));

        player = dungeon.getPlayer();
        exit = dungeon.getExit();
    }

    @Test
    void startInactive() {
        assertEquals(exit.getStatus(), false);
    }

    @Test
    void activatedByPlayer() {
        player.moveTo(1, 2);
        assertEquals(exit.getStatus(), true);
    }

    @Test
    void notActivatedByEnemy() {
        Enemy enemy = new Enemy(dungeon, 1, 2);
        dungeon.addEntity(enemy);
        assertEquals(exit.getStatus(), false);
    }

    @Test
    void notActivatedByBoulder() {
        player.moveTo(1, 0);
        player.moveDown();
        assertEquals(exit.getStatus(), false);
    }

    @Test
    void toggleable() {
        player.moveTo(1, 2);
        assertEquals(exit.getStatus(), true);

        player.moveDown();
        assertEquals(exit.getStatus(), false);

        player.moveUp();
        assertEquals(exit.getStatus(), true);
    }

    /**
     * Successfully interacts with the exit despite interacting with
     * as many other things as possible on the same square.
     */
    @Test
    void busySquare() {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 0, 0))
                .put(JSONFactory.entity("key", 0, 0, 1))
                .put(JSONFactory.entity("sword", 0, 0))

                .put(JSONFactory.entity("exit", 1, 2))

                .put(JSONFactory.entity("key", 1, 2, 1))
                .put(JSONFactory.entity("door", 1, 2, 1))
                .put(JSONFactory.entity("treasure", 1, 2))
                .put(JSONFactory.entity("sword", 1, 2))
                .put(JSONFactory.entity("invincibility", 1, 2))
                .put(JSONFactory.entity("switch", 1, 2))
                .put(JSONFactory.entity("portal", 1, 2, 1))
                .put(JSONFactory.entity("portal", 1, 2, 1)),
            JSONFactory.goal("treasure")
        ));

        player = dungeon.getPlayer();
        exit = dungeon.getExit();

        // Pickup a key and a sword to open the door and kill the enemy
        // upon entering the square.
        player.moveTo(0, 0);

        // Add the enemy now - adding it beforehand would've allowed it to
        // chase the player.
        Enemy enemy = new Enemy(dungeon, 1, 2);
        dungeon.addEntity(enemy);

        player.moveTo(1, 2);
        assertEquals(exit.getStatus(), true);
    }
}
