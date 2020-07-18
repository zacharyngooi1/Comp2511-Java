package unsw.dungeon;

public class Boulder extends MoveableEntity {
    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.BOULDER, true, dungeon);
    }
}
