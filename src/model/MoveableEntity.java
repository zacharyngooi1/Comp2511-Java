package model;

import java.util.List;

/**
 * A moveable entity in the dungeon.
 *
 */
public class MoveableEntity extends Entity {
    public MoveableEntity(int x, int y, Tag tag, CollisionLayer collisionLayer, Dungeon dungeon) {
        super(x, y, tag, collisionLayer, dungeon);
    }

    /**
     * Try to move this entity to the square at (x, y).
     * @param x the x co-ordinate of the destination.
     * @param y the y co-ordinate of the destination.
     * @param considerPortals whether or not to interacts with portals at
     * the destination. Exists to avoid infinite portal loops.
     * @return whether or not the movement was successful.
     */
    public boolean moveTo(int x, int y, boolean considerPortals) {
        if (x < 0 || y < 0 || x >= dungeon.getWidth() || y >= dungeon.getHeight()) {
            return false;
        }

        List<Entity> entitiesAtSquare = dungeon.getEntitiesAtSquare(x, y);

        for (Entity entity : entitiesAtSquare) {
            if (entity != null && collidesWith(entity) && entity != this) {
                return false;
            }
        }
        int prevX = getX();
        int prevY = getY();
        setX(x);
        setY(y);

        for (Entity entity : dungeon.getEntitiesAtSquare(prevX, prevY)) {
            if (entity != null && entity != this) {
                entity.onEntityExit(this);
                this.onEntityExit(entity);
            }
        }

        for (Entity entity : entitiesAtSquare) {
            if (entity != null && entity != this) {
                if (entity.getTag() == Tag.PORTAL && !considerPortals) {
                    continue;
                }

                entity.onEntityEnter(this);
                this.onEntityEnter(entity);
            }
        }
        return true;
    }

    /**
     * Try to move this entity to the square at (x, y). The entity
     * will interact with any portals at the destination if the movement is
     * successful.
     * @param x the x co-ordinate of the destination.
     * @param y the y co-ordinate of the destination.
     * @return whether or not the movement was successful.
     */
    public boolean moveTo(int x, int y) {
        return moveTo(x, y, true);
    }
}
