package unsw.dungeon;

/**
 * The enemy entity.
 */
public class Enemy extends MoveableEntity {
    /**
     * Create a enemy positioned in square (x, y).
     * @param x
     * @param y
     */
    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.ENEMY, false, dungeon);
    }
}
