package test;

import org.json.JSONObject;

import unsw.dungeon.*;

/**
 * Provides a thin wrapper around DungeonLoader that omits any implementation
 * of the view and only loads a dungeon from json. This serves to streamline
 * tests requiring dungeons.
 */
public class DungeonMockLoader extends DungeonLoader {
    /**
     * Parses a dungeon in json form into a corresponding Dungeon object.
     * @return dungeon
     */
    public static Dungeon parseDungeon(JSONObject json) {
        return new DungeonMockLoader(json).load();
    }

    private DungeonMockLoader(JSONObject json) {
        super(json);
    }

    @Override
    public void onLoad(Player player) {
        ;
    }

    @Override
    public void onLoad(Wall wall) {
        ;
    }

    @Override
    public void onLoad(Boulder boulder) {
        ;
    }

    @Override
    public void onLoad(Enemy enemy) {
        ;
    }

    @Override
    public void onLoad(Treasure treasure) {
        ;
    }

    @Override
    public void onLoad(Exit exit) {
        ;
    }

    @Override
    public void onLoad(Key key) {
        ;
    }

    @Override
    public void onLoad(FloorSwitch floorSwitch) {
        ;
    }

    @Override
    public void onLoad(Invincibility invincibility) {
        ;
    }

    @Override
    public void onLoad(Door door) {
        ;
    }

    @Override
    public void onLoad(Portal portal) {
        ;
    }

    @Override
    public void onLoad(Sword sword) {
        ;
    }
}
