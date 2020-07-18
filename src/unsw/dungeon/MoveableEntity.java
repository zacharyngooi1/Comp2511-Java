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
     * @return whether or not the movement was successful.
     */
    public boolean moveTo(int x, int y) {
        if (x < 0 || y < 0 || x >= dungeon.getWidth() || y >= dungeon.getHeight()) {
            return false;
        }

        Entity entityAtSquare = dungeon.getEntityAtSquare(x, y);

        if (entityAtSquare == null) {
            setX(x);
            setY(y);
            return true;
        } else if (!entityAtSquare.isCollidable()) {
            setX(x);
            setY(y);
            entityAtSquare.onEntityEnter(this);
            return true;
        } else {
            return false;
        }
    }
}
