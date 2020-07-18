package unsw.dungeon;

public class Door extends Entity {
    private int id;

    public Door(Dungeon dungeon, int x, int y, int id) {
        super(x, y, Tag.DOOR, true, dungeon);
        this.id = id;
    }

    public int getDoorId() {
        return this.id;
    }
}
