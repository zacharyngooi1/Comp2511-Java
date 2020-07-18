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

    public void moveAwayFromPlayer(int x, int y) {
        double initialDistance = calDis(x, y, getX(), getY());

        for (int i = -1; i <= 1; i++) {
            Entity entity = this.dungeon.getsingleEntity(getX() + i, getY());
            if (entity == null) {
                if (calDis(getX() + i, getY(), x, y) > initialDistance) {
                    moveTo(getX() + i, getY());
                    return;
                }
            }
            else if (!entity.isCollidable()) {
                if (calDis(getX() + i, getY(), x, y) > initialDistance) {
                    moveTo(getX() + i, getY());
                    return;
                }
            }
        }
        for (int g = -1; g <= 1; g++) {
            Entity entity = this.dungeon.getsingleEntity(getX(), getY() + g);
            if (entity == null) {
                if (calDis(getX(), getY() + g, x, y) > initialDistance) {
                    moveTo(getX(), getY() + g);
                    return;
                }
            }
            else if (!entity.isCollidable()) {
                if (calDis(getX(), getY() + g, x, y) > initialDistance) {
                    moveTo(getX(), getY() + g);
                    return;
                }
            }
        }
    }

    public double calDis(int x1,int y1,int x2,int y2)
	{
	    return (Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)));
	}
}
