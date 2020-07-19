package unsw.dungeon;

public class Exit extends Entity {
    public Exit(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.EXIT, new CollisionLayer(0), dungeon);
    }
}
