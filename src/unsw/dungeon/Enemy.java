package unsw.dungeon;

public class Enemy extends MoveableEntity {
    private boolean directionToggle;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.ENEMY, false, dungeon);
        directionToggle = false;
        dungeon.getPlayer().attachEnemy(this);
    }


    /**
     * @param x the x co-ordinate of the player.
     * @param y the y co-ordinate of the player.
     */
    public void moveTowardsPlayer(int x, int y) {
        int xMovement = 0;
        int yMovement = 0;

        if (x > getX()) {
            xMovement = 1;
        } else if (x < getX()) {
            xMovement = -1;
        }

        if (y > getY()) {
            yMovement = 1;
        } else if (y < getY()) {
            yMovement = -1;
        }

        // Only allow at most one direction of successful movement
        if (directionToggle) {
            if (xMovement == 0 || !moveTo(getX() + xMovement, getY())) {
                moveTo(getX(), getY() + yMovement);
            }
        } else {
            if (yMovement == 0 || !moveTo(getX(), getY() + yMovement)) {
                moveTo(getX() + xMovement, getY());
            }
        }

        // Flip the boolean
        directionToggle = !directionToggle;
    }
}
