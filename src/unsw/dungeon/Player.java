package unsw.dungeon;

import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

/**
 * The player entity.
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MoveableEntity {
    private List<Enemy> enemies;
    private List<Key> keys;
    private Sword sword;
    private Invincibility invincibility;
    private int treasure;

    /**
     * Create a player positioned in square (x, y).
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.PLAYER, true, dungeon);
        enemies = new ArrayList<Enemy>();
        keys = new ArrayList<Key>();
        treasure = 0;
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

    public void attachEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void detatchEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public boolean hasConsumable(Tag tag) {
        switch (tag) {
            case SWORD:
                return !(sword == null);
            case INVINCIBILITY:
                return !(invincibility == null);
            default:
                return false;
        }
    }

    /**
     * Called whenever a player's consumable runs out.
     */
    public void consumableDepleted(Tag tag) {
        switch (tag) {
            case SWORD:
                sword = null;
                break;
            case INVINCIBILITY:
                invincibility = null;
                break;
            default:
                break;
        }
    }

    @Override
    public boolean moveTo(int x, int y) {
        int xMovement = x - getX();
        int yMovement = y - getY();

        // If the player is moving normally, i.e. not if they're being
        // teleported, check if they can move a boulder
        if (Math.abs(xMovement) <= 1 && Math.abs(yMovement) <= 1) {
            TryPushBoulder(x, y, xMovement, yMovement);
        }

        boolean res = super.moveTo(x, y);

        if (res) {
            if (invincibility != null) {
                invincibility.setValue(invincibility.getValue() - 1);
            }

            notifyEnemies();
        }

        return res;
    }

    /**
     * @param x the x co-ordinate of the boulder.
     * @param y the y co-ordinate of the boulder.
     * @param xPush how much to push the boulder by in the x direction.
     * @param yPush how much to push the boulder by in the y direction.
     */
    private void TryPushBoulder(int x, int y, int xPush, int yPush) {
        for (Entity entity : dungeon.getEntitiesAtSquare(x, y)) {
            if (entity != null && entity.getTag() == Tag.BOULDER) {
                Boulder boulder = (Boulder) entity;
                boulder.moveTo(boulder.getX() + xPush, boulder.getY() + yPush);
            }
        }
    }

    /**
     * Notifies all enemy listeners that the player has moved.
     */
    private void notifyEnemies() {
        for (Enemy enemy : enemies) {
            enemy.moveTowardsPlayer(getX(), getY());
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Player destroyed, level should restart");
        // then restart level
    }

    @Override
    public void onEntityEnter(Entity other) {
        super.onEntityEnter(other);

        switch (other.getTag()) {
            case TREASURE:
                break;
            case KEY:
                break;
            case ENEMY:
                onEnemyEnter((Enemy) other);
                break;
            case SWORD:
                onSwordEnter((Sword) other);
                break;
            case INVINCIBILITY:
                onInvincibilityEnter((Invincibility) other);
                break;
            default:
                break;
        }
    }

    private void onEnemyEnter(Enemy enemy) {
        if (sword == null) {
            destroy();
        } else {
            sword.setValue(sword.getValue() - 1);
            detatchEnemy(enemy);
            enemy.destroy();
        }
    }

    private void onSwordEnter(Sword sword) {
        if (this.sword == null) {
            this.sword = sword;
        } else {
            // Repair sword
            this.sword.setValue(this.sword.getMaxValue());
        }
    }

    private void onInvincibilityEnter(Invincibility invincibility) {
        if (this.invincibility == null) {
            this.invincibility = invincibility;
        } else {
            // Replenish invincibility
            this.invincibility.setValue(this.invincibility.getMaxValue());
        }
    }
}
