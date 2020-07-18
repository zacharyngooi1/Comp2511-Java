package unsw.dungeon;

public class Key extends Entity {
    public Key(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.KEY, false, dungeon);
    }
}
