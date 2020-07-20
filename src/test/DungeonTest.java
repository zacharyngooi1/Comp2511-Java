package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.json.JSONArray;

import unsw.dungeon.*;

public class DungeonTest {
    @Test
    public void blahTest(){
        Dungeon dungeon = DungeonMockLoader.parseDungeon(JSONFactory.dungeon(
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
        assertEquals(dungeon.getHeight(), 16);
    }
    
    @Test
    public void blahTest2(){
        Dungeon d = new Dungeon(1, 2);
        assertEquals(d.getWidth(), 1);
    }
}
