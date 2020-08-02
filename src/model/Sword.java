package model;

public class Sword extends ConsumableEntity {
    public Sword(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, Tag.SWORD, 5);
    }
}
