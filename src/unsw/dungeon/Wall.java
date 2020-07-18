package unsw.dungeon;

public class Wall extends Entity {
    public Wall(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.WALL, true, dungeon);
    }
}
