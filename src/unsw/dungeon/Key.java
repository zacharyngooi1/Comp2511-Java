package unsw.dungeon;

public class Key extends Entity {
    private int id;

    public Key(Dungeon dungeon, int x, int y, int id) {
        super(x, y, Tag.KEY, new CollisionLayer(0), dungeon);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
