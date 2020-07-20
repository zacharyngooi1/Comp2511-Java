package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.*;

public class GoalsTest {
    private Dungeon dungeon;
    private Player player;

    void initialize(JSONObject goalCondition) {
        dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            new JSONArray()
                .put(JSONFactory.entity("player", 1, 1))
                .put(JSONFactory.entity("sword", 1, 2))

                // Enemies are boxed in.
                .put(JSONFactory.entity("enemy", 5, 1))
                .put(JSONFactory.entity("wall", 4, 1))
                .put(JSONFactory.entity("wall", 6, 1))
                .put(JSONFactory.entity("wall", 5, 0))
                .put(JSONFactory.entity("wall", 5, 2))
                .put(JSONFactory.entity("enemy", 5, 3))
                .put(JSONFactory.entity("wall", 4, 3))
                .put(JSONFactory.entity("wall", 6, 3))
                .put(JSONFactory.entity("wall", 5, 4))

                .put(JSONFactory.entity("boulder", 7, 1))
                .put(JSONFactory.entity("switch", 7, 2))
                .put(JSONFactory.entity("boulder", 8, 1))
                .put(JSONFactory.entity("switch", 8, 2))

                .put(JSONFactory.entity("treasure", 9, 1))
                .put(JSONFactory.entity("treasure", 9, 2))

                .put(JSONFactory.entity("exit", 0, 0)),
            goalCondition
        ));

        player = dungeon.getPlayer();
    }

    private void killFirstEnemy() {
        player.moveTo(1, 2);
        player.moveTo(5, 1);
    }

    private void killSecondEnemy() {
        player.moveTo(1, 2);
        player.moveTo(5, 3);
    }

    private void activateFirstSwitch() {
        player.moveTo(7, 0);
        player.moveDown();
    }

    private void activateSecondSwitch() {
        player.moveTo(8, 0);
        player.moveDown();
    }

    private void deactivateFirstSwitch() {
        player.moveTo(7, 3);
        player.moveUp();
    }

    private void pickupFirstTreasure() {
        player.moveTo(9, 1);
    }

    private void pickupSecondTreasure() {
        player.moveTo(9, 2);
    }

    private void moveToExit() {
        player.moveTo(0, 0);
    }

    @Test
    public void enemies() {
        initialize(
            JSONFactory.goal("enemies")
        );

        assertEquals(dungeon.checkGameWon(), false);

        killFirstEnemy();
        assertEquals(dungeon.checkGameWon(), false);
        killSecondEnemy();
        assertEquals(dungeon.checkGameWon(), true);
    }

    @Test
    public void boulders() {
        initialize(
            JSONFactory.goal("boulders")
        );

        assertEquals(dungeon.checkGameWon(), false);

        activateFirstSwitch();
        assertEquals(dungeon.checkGameWon(), false);
        activateSecondSwitch();
        assertEquals(dungeon.checkGameWon(), true);

        deactivateFirstSwitch();
        assertEquals(dungeon.checkGameWon(), false);

        activateFirstSwitch();
        assertEquals(dungeon.checkGameWon(), true);
    }

    @Test
    public void treasure() {
        initialize(
            JSONFactory.goal("treasure")
        );

        assertEquals(dungeon.checkGameWon(), false);

        pickupFirstTreasure();
        assertEquals(dungeon.checkGameWon(), false);
        pickupSecondTreasure();
        assertEquals(dungeon.checkGameWon(), true);
    }

    @Test
    public void exit() {
        initialize(
            JSONFactory.goal("exit")
        );

        assertEquals(dungeon.checkGameWon(), false);

        moveToExit();
        assertEquals(dungeon.checkGameWon(), true);

        player.moveDown();
        assertEquals(dungeon.checkGameWon(), false);

        moveToExit();
        assertEquals(dungeon.checkGameWon(), true);
    }

    @Test
    public void and() {
        initialize(
            JSONFactory.goal(
                "AND",
                new JSONArray()
                    .put(JSONFactory.goal("enemies"))
                    .put(JSONFactory.goal("boulders"))
                    .put(JSONFactory.goal("treasure"))
                    .put(JSONFactory.goal("exit"))
            )
        );

        assertEquals(dungeon.checkGameWon(), false);

        killFirstEnemy();
        assertEquals(dungeon.checkGameWon(), false);
        killSecondEnemy();
        assertEquals(dungeon.checkGameWon(), false);

        activateFirstSwitch();
        assertEquals(dungeon.checkGameWon(), false);
        activateSecondSwitch();
        assertEquals(dungeon.checkGameWon(), false);

        pickupFirstTreasure();
        assertEquals(dungeon.checkGameWon(), false);
        pickupSecondTreasure();

        moveToExit();
        assertEquals(dungeon.checkGameWon(), true);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    public void or(int option) {
        initialize(
            JSONFactory.goal(
                "OR",
                new JSONArray()
                    .put(JSONFactory.goal("enemies"))
                    .put(JSONFactory.goal("boulders"))
                    .put(JSONFactory.goal("treasure"))
                    .put(JSONFactory.goal("exit"))
            )
        );

        assertEquals(dungeon.checkGameWon(), false);

        switch (option) {
            case 0:
                killFirstEnemy();
                assertEquals(dungeon.checkGameWon(), false);
                killSecondEnemy();
                break;
            case 1:
                activateFirstSwitch();
                assertEquals(dungeon.checkGameWon(), false);
                activateSecondSwitch();
                break;
            case 2:
                moveToExit();
                break;
            case 3:
                pickupFirstTreasure();
                assertEquals(dungeon.checkGameWon(), false);
                pickupSecondTreasure();
                break;
            default:
                break;
        }

        assertEquals(dungeon.checkGameWon(), true);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void andOr(int option) {
        initialize(
            JSONFactory.goal(
                "AND",
                new JSONArray()
                    .put(JSONFactory.goal("enemies"))
                    .put(JSONFactory.goal("boulders"))
                    .put(JSONFactory.goal(
                        "OR",
                        new JSONArray()
                            .put(JSONFactory.goal("treasure"))
                            .put(JSONFactory.goal("exit"))
                    ))
            )
        );

        assertEquals(dungeon.checkGameWon(), false);

        killFirstEnemy();
        assertEquals(dungeon.checkGameWon(), false);
        killSecondEnemy();
        assertEquals(dungeon.checkGameWon(), false);

        activateFirstSwitch();
        assertEquals(dungeon.checkGameWon(), false);
        activateSecondSwitch();
        assertEquals(dungeon.checkGameWon(), false);

        switch (option) {
            case 0:
                moveToExit();
                break;
            case 1:
                pickupFirstTreasure();
                assertEquals(dungeon.checkGameWon(), false);
                pickupSecondTreasure();
                break;
            default:
                break;
        }

        assertEquals(dungeon.checkGameWon(), true);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    public void repeatedBasicGoal(int option) {
        initialize(
            JSONFactory.goal(
                "AND",
                new JSONArray()
                    .put(JSONFactory.goal(
                        "OR",
                        new JSONArray()
                            .put(JSONFactory.goal("treasure"))
                            .put(JSONFactory.goal("boulders"))
                    ))
                    .put(JSONFactory.goal(
                        "OR",
                        new JSONArray()
                            .put(JSONFactory.goal("boulders"))
                            .put(JSONFactory.goal("exit"))
                    ))
            )
        );

        assertEquals(dungeon.checkGameWon(), false);

        switch (option) {
            case 0:
                pickupFirstTreasure();
                assertEquals(dungeon.checkGameWon(), false);
                pickupSecondTreasure();
                assertEquals(dungeon.checkGameWon(), false);
                moveToExit();
                break;
            case 1:
                activateFirstSwitch();
                assertEquals(dungeon.checkGameWon(), false);
                activateSecondSwitch();
                break;
            default:
                break;
        }

        assertEquals(dungeon.checkGameWon(), true);
    }
}
