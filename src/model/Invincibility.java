package model;

public class Invincibility extends ConsumableEntity {
    public Invincibility(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, Tag.INVINCIBILITY, 20);
    }
}
