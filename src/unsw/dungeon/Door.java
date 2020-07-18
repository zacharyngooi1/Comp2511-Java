package unsw.dungeon;

public class Door extends Entity {
    public Door(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.DOOR, false, dungeon);
    }
}
