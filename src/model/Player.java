package model;

import controllers.AnimationController;

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
    private List<Treasure> treasures;
    private Sword sword;
    private Invincibility invincibility;
    private AnimationController animationController;

    /**
     * Create a player positioned in square (x, y).
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.PLAYER, new CollisionLayer(CollisionLayer.PLAYER), dungeon);
        enemies = new ArrayList<>();
        keys = new ArrayList<>();
        treasures = new ArrayList<>();
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

    public Sword getSword() {
        return this.sword;
    }

    public Invincibility getInvincibility() {
        return this.invincibility;
    }

    public List<Key> getKeys() {
        return this.keys;
    }

    public List<Treasure> getTreasures() {
        return this.treasures;
    }

    public void setAnimationController(AnimationController animationController) {
        this.animationController = animationController;
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

        checkDoor(x, y);

        // If the player is moving normally, i.e. not if they're being
        // teleported, check if they can move a boulder.
        if (Math.abs(xMovement) <= 1 && Math.abs(yMovement) <= 1) {
            checkBoulder(x, y, xMovement, yMovement);
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
     * If the player is going to move to a door, check if they can
     * open the door.
     * @param x the x co-ordinate to check for a door.
     * @param y the y co-ordinate to check for a door.
     */
    private void checkDoor(int x, int y) {
        for (Entity entity : dungeon.getEntitiesAtSquare(x, y)) {
            if (entity != null && entity.getTag() == Tag.DOOR) {
                attemptDoorOpen((Door) entity);
            }
        }
    }

    private void attemptDoorOpen(Door door) {
        // Must iterate like this because keys clearly may be modified
        for (int i = 0; i < keys.size(); i++) {
            if (door.tryUnlock(keys.get(i))) {
                keys.remove(keys.get(i));
            }
        }
    }

    /**
     * @param x the x co-ordinate to check for a boulder.
     * @param y the y co-ordinate to check for a boulder.
     * @param xPush how much to push the boulder by in the x direction.
     * @param yPush how much to push the boulder by in the y direction.
     */
    private void checkBoulder(int x, int y, int xPush, int yPush) {
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
        // Must iterate like this because enemies might be modified if the
        // enemy enters the player and is killed
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).playerMoved(getX(), getY(), hasConsumable(Tag.INVINCIBILITY));
        }
    }

    @Override
    public void onEntityEnter(Entity other) {
        super.onEntityEnter(other);

        switch (other.getTag()) {
            case TREASURE:
                onTreasureEnter((Treasure) other);
                break;
            case KEY:
                onKeyEnter((Key) other);
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
            case PORTAL:
                onPortalEnter((Portal) other);
                break;
            default:
                break;
        }
    }

    private void onTreasureEnter(Treasure treasure) {
        treasure.removeFromDungeon();
        treasures.add(treasure);
        animationController.transition("pickup");
    }

    private void onKeyEnter(Key key) {
        keys.add(key);
        key.removeFromDungeon();
        animationController.transition("pickup");
    }

    private void onEnemyEnter(Enemy enemy) {
        if (sword != null || invincibility != null) {
            if (sword != null && invincibility == null) {
                sword.setValue(sword.getValue() - 1);
            }

            enemy.removeFromDungeon();
            animationController.transition("attack");
        } else {
            removeFromDungeon();
        }
    }

    private void onSwordEnter(Sword sword) {
        if (this.sword == null) {
            this.sword = sword;
        } else {
            this.sword.setValue(this.sword.getMaxValue());
        }

        animationController.transition("pickup");
    }

    private void onInvincibilityEnter(Invincibility invincibility) {
        if (this.invincibility == null) {
            this.invincibility = invincibility;
        } else {
            this.invincibility.setValue(this.invincibility.getMaxValue());
        }

        animationController.transition("pickup");
    }

    private void onPortalEnter(Portal portal) {
        animationController.transition("teleport");
    }
}
