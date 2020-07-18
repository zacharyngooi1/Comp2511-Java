package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {
    private Dungeon dungeon;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void moveUp() {
        if (getY() > 0) {
            setY(getY() - 1);
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
            setY(getY() + 1);
        }
    }

    public void moveLeft() {
        if (getX() > 0) {
            setX(getX() - 1);
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            setX(getX() + 1);
        }
    }
}
