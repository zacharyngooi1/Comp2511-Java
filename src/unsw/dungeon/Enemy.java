package unsw.dungeon;

public class Enemy extends MoveableEntity {
    private boolean directionToggle;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.ENEMY, false, dungeon);
        directionToggle = false;
        dungeon.getPlayer().attachEnemy(this);
    }

    public void playerMoved(int x, int y, boolean isInvincible) {
        if (isInvincible) {
            moveAwayFromPlayer(x, y);
        } else {
            moveTowardsPlayer(x, y);
        }
    }

    /**
     * @param x the x co-ordinate of the player.
     * @param y the y co-ordinate of the player.
     */
    private void moveTowardsPlayer(int x, int y) {
        double initialDistance = calDis(x, y, getX(), getY());

        for (int xMovement = -1; xMovement <= 1; xMovement++) {
            double newDistance = calDis(getX() + xMovement, getY(), x, y);
            if (newDistance < initialDistance) {
                if (moveTo(getX() + xMovement, getY())) {
                    return;
                }
            }
        }

        for (int yMovement = -1; yMovement <= 1; yMovement++) {
            double newDistance = calDis(getX(), getY() + yMovement, x, y);
            if (newDistance < initialDistance) {
                if (moveTo(getX(), getY() + yMovement)) {
                    return;
                }
            }
        }
    }

    /**
     * @param x the x co-ordinate of the player.
     * @param y the y co-ordinate of the player.
     */
    private void moveAwayFromPlayer(int x, int y) {
        double initialDistance = calDis(x, y, getX(), getY());

        for (int xMovement = -1; xMovement <= 1; xMovement++) {
            double newDistance = calDis(getX() + xMovement, getY(), x, y);
            if (newDistance > initialDistance) {
                if (moveTo(getX() + xMovement, getY())) {
                    return;
                }
            }
        }

        for (int yMovement = -1; yMovement <= 1; yMovement++) {
            double newDistance = calDis(getX(), getY() + yMovement, x, y);
            if (newDistance > initialDistance) {
                if (moveTo(getX(), getY() + yMovement)) {
                    return;
                }
            }
        }
    }

    /**
     * @param x1 x co-ordinate of point 1
     * @param y1 y co-ordinate of point 1
     * @param x2 x co-ordinate of point 2
     * @param y2 y co-ordinate of point 2
     * @return the distance between point 1 and point 2 derived from the
     * Pythagorean Theorem.
     */
    public double calDis(int x1,int y1,int x2,int y2)
	{
	    return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }
}
