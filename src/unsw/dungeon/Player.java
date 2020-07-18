package unsw.dungeon;

import java.lang.Math;
import java.util.ArrayList;

/**
 * The player entity
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
            Entity entityAtSquare = dungeon.getEntityAtSquare(x, y);

            if (entityAtSquare != null && entityAtSquare.getTag() == Tag.BOULDER) {
                Boulder boulder = (Boulder) entityAtSquare;

                // Move the boulder in the direction the player is going. The
                // boulder will do its own collision checking, preventing it from
                // moving onto other boulders and walls
                boulder.moveTo(boulder.getX() + xMovement, boulder.getY() + yMovement);
            }
        }

       Entity entityAtSquare = dungeon.getEntityAtSquare(x, y);
       
       if (entityAtSquare != null && entityAtSquare.getTag() == Tag.PORTAL) {
           int id = 0;
           // check if the portal has an existing pair with the same id
           // portalHasPair will return the pairing portal
            for (Portal p: this.dungeon.getPortalList()) {
                if (p != null) {
                    if (p.getX() == entityAtSquare.getX() && p.getY() == entityAtSquare.getY()) {
                        id = p.getID();
                    }
                }

            }

            for (Portal p: this.dungeon.getPortalList()) {
                if (p != null) {
                    if (p.getX() != entityAtSquare.getX() && p.getY() != entityAtSquare.getY() && p.getID() == id) {
                        return super.moveTo(p.getX(), p.getY());
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
            case POTION:
                break;
            default:
                break;
        }
    }
}
