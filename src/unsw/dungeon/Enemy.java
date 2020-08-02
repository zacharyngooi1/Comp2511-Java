package unsw.dungeon;

public class Enemy extends MoveableEntity {
    private AnimationController animationController;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.ENEMY, new CollisionLayer(CollisionLayer.ENEMY), dungeon);
        dungeon.getPlayer().attachEnemy(this);
    }

    public void setAnimationController(AnimationController animationController) {
        this.animationController = animationController;
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

        // If moving left/up/right/down (in that order) would decrease
        // the distance from the enemy and the player, try moving in that
        // direction.
        for (int offset : new int[]{-1, 1}) {
            if (calDis(getX() + offset, getY(), x, y) < initialDistance) {
                if (moveTo(getX() + offset, getY())) {
                    return;
                }
            }
;
            if (calDis(getX(), getY() + offset, x, y) < initialDistance) {
                if (moveTo(getX(), getY() + offset)) {
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

        for (int offset : new int[]{-1, 1}) {
            if (calDis(getX() + offset, getY(), x, y) > initialDistance) {
                if (moveTo(getX() + offset, getY())) {
                    return;
                }
            }
;
            if (calDis(getX(), getY() + offset, x, y) > initialDistance) {
                if (moveTo(getX(), getY() + offset)) {
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
    public double calDis(int x1, int y1, int x2, int y2) {
	    return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

    @Override
    public void onEntityEnter(Entity other) {
        super.onEntityEnter(other);

        switch (other.getTag()) {
            case PLAYER:
                animationController.transition("attack");
                break;
            default:
                break;
        }
    }
}
