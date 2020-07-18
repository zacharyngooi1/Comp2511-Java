package unsw.dungeon;

public class Sword extends Entity {
    public Sword(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.SWORD, false, dungeon);
    }
}
