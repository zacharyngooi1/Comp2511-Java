package unsw.dungeon;

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
