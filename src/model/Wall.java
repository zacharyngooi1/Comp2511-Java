package model;

public class Wall extends Entity {
    public Wall(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.WALL, new CollisionLayer(CollisionLayer.PLAYER | CollisionLayer.ENEMY), dungeon);
    }
}
