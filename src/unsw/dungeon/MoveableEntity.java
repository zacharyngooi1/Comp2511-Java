package unsw.dungeon;

/**
 * A moveable entity in the dungeon.
 *
 */
public class MoveableEntity extends Entity {
    public MoveableEntity(int x, int y, Tag tag, boolean isCollidable, Dungeon dungeon) {
        super(x, y, tag, isCollidable, dungeon);
    }

    /**
     * Try to move this entity to the square at (x, y).
     */
    public void moveTo(int x, int y) {
        if (x < 0 || y < 0 || x >= dungeon.getWidth() || y >= dungeon.getHeight()) {
            return;
        }

        Entity entityAtSquare = dungeon.getEntityAtSquare(x, y);

        if (entityAtSquare == null) {
            setX(x);
            setY(y);
        } else if (!entityAtSquare.isCollidable()) {
            setX(x);
            setY(y);
            entityAtSquare.onEntityEnter(this);
        }
    }
}
