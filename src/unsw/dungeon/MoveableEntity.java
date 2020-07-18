package unsw.dungeon;

import java.util.List;

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

        List<Entity> entitiesAtSquare = dungeon.getEntitiesAtSquare(x, y);

        for (Entity entity : entitiesAtSquare) {
            if (entity != null && entity.isCollidable() && entity != this) {
                return false;
            }
        }

        for (Entity entity : entitiesAtSquare) {
            if (entity != null && entity != this) {
                entity.onEntityEnter(this);
                this.onEntityEnter(entity);
            }
        }
        
        setX(x);
        setY(y);

        System.out.println(getTag() + " -> (" + x + ", " + y + ")");

        return true;
    }
}
