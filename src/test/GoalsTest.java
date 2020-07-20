package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.json.JSONArray;

import unsw.dungeon.*;

public class GoalsTest {
    JSONArray entities = new JSONArray()
        .put(JSONFactory.entity("player", 1, 1))
        .put(JSONFactory.entity("sword", 1, 2))

        // Enemies are boxed in
        .put(JSONFactory.entity("enemy", 5, 1))
        .put(JSONFactory.entity("wall", 4, 1))
        .put(JSONFactory.entity("wall", 6, 1))
        .put(JSONFactory.entity("wall", 5, 0))
        .put(JSONFactory.entity("wall", 5, 2))
        .put(JSONFactory.entity("enemy", 5, 3))
        .put(JSONFactory.entity("wall", 4, 3))
        .put(JSONFactory.entity("wall", 6, 3))
        .put(JSONFactory.entity("wall", 5, 4))

        .put(JSONFactory.entity("boulder", 6, 1))
        .put(JSONFactory.entity("switch", 6, 2))
        .put(JSONFactory.entity("boulder", 7, 1))
        .put(JSONFactory.entity("switch", 7, 2))

        .put(JSONFactory.entity("treasure", 8, 1))
        .put(JSONFactory.entity("treasure", 8, 2))

        .put(JSONFactory.entity("exit", 9, 1));

    @Test
    public void enemies() {
        Dungeon dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            entities,
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
        ));

        Player player = dungeon.getPlayer();
        // Pickup sword
        player.moveTo(1, 2);
    }

    @Test
    public void andOr() {
        Dungeon dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
            10,
            10,
            entities,
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
        ));
    }
}
