package test;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Shorthands for creating JSONObjects for loading dungeons.
 */
public class JSONFactory {
    /*
        DungeonMockLoader loader = new DungeonMockLoader(JSONFactory.dungeon(
            18,
            16,
            new JSONArray()
                .put(JSONFactory.entity("wall", 0, 0)),
            JSONFactory.goal(
                "AND",
                new JSONArray()
                    .put(JSONFactory.goal("enemies"))
                    .put(JSONFactory.goal("boulders"))
                    .put(JSONFactory.goal("treasure"))
                    .put(JSONFactory.goal("exit"))
            )
        ));
    */
    public static JSONObject dungeon(int width, int height, JSONArray entities, JSONObject goalCondition) {
        return new JSONObject()
            .put("width", width)
            .put("height", height)
            .put("entities", entities)
            .put("goal-condition", goalCondition);
    }

    public static JSONObject entity(String type, int x, int y) {
        return new JSONObject()
            .put("type", type)
            .put("x", x)
            .put("y", y);
    }

    public static JSONObject entity(String type, int x, int y, int id) {
        return new JSONObject()
            .put("type", type)
            .put("x", x)
            .put("y", y)
            .put("id", id);
    }

    public static JSONObject goal(String goal) {
        return new JSONObject()
            .put("goal", goal);
    }

    public static JSONObject goal(String goal, JSONArray subgoals) {
        return new JSONObject()
            .put("goal", goal)
            .put("subgoals", subgoals);
    }
}
