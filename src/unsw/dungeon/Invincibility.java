package unsw.dungeon;

public class Invincibility extends Entity {
    public Invincibility(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.INVINCIBILITY, false, dungeon);
    }
}
