package unsw.dungeon;

import java.lang.Math;
import java.util.List;

/**
 * The player entity.
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MoveableEntity {
    /**
     * Create a player positioned in square (x, y).
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.PLAYER, true, dungeon);
    }

    public void moveUp() {
        moveTo(getX(), getY() - 1);
    }

    public void moveDown() {
        moveTo(getX(), getY() + 1);
    }

    public void moveLeft() {
        moveTo(getX() - 1, getY());
    }

    public void moveRight() {
        moveTo(getX() + 1, getY());
    }

    @Override
    public boolean moveTo(int x, int y) {
        int xMovement = x - getX();
        int yMovement = y - getY();

        // If the player is moving normally, i.e. not if they're being
        // teleported, check if they can move a boulder
        if (Math.abs(xMovement) <= 1 && Math.abs(yMovement) <= 1) {
            for (Entity entity : dungeon.getEntitiesAtSquare(x, y)) {
                if (entity != null && entity.getTag() == Tag.BOULDER) {
                    Boulder boulder = (Boulder) entity;

                    // Move the boulder in the direction the player is going. The
                    // boulder will do its own collision checking, preventing it from
                    // moving onto other boulders and walls
                    boulder.moveTo(boulder.getX() + xMovement, boulder.getY() + yMovement);
                }
            }
        }

       
       for (Entity e : dungeon.getEntitiesAtSquare(x, y)) {
            if (e != null && e.getTag() == Tag.PORTAL) {
                int id = 999999;
                // check if the portal has an existing pair with the same id
                // Cleanup required
                for (Portal p: this.dungeon.getPortalList()) {
                        if (p != null) {
                            if (p.getX() == e.getX() && p.getY() == e.getY()) {
                                id = p.getID();
                            }
                        }

                    }

                for (Portal p: this.dungeon.getPortalList()) {
                    if (p != null) {
                        if (p.getX() != e.getX() && p.getY() != e.getY() && p.getID() == id) {
                            return super.moveTo(p.getX(), p.getY());
                        }
                    }
                }
            }
       }

        return super.moveTo(x, y);
    }

    @Override
    public void onEntityEnter(Entity other) {
        super.onEntityEnter(other);

        switch (other.getTag()) {
            case EXIT:
                break;
            case TREASURE:
                break;
            case DOOR:
                break;
            case KEY:
                break;
            case BOULDER:
                break;
            case PORTAL:
                break;
            case ENEMY:
                break;
            case SWORD:
                break;
            case INVINCIBILITY:
                break;
            default:
                break;
        }
    }
}
