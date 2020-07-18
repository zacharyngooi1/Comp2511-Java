package unsw.dungeon;

public class Treasure extends Entity {
    public Treasure(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.TREASURE, false, dungeon);
    }
}
