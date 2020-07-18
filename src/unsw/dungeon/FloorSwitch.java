package unsw.dungeon;

public class FloorSwitch extends Entity {
    public FloorSwitch(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.FLOORSWITCH, false, dungeon);
    }
}
