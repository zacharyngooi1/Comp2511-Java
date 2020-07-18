package unsw.dungeon;

public class Portal extends Entity {
    public Portal(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.PORTAL, false, dungeon);
    }
}
