package unsw.dungeon;

public class CollisionLayer {
    public static final int PLAYER = 1;
    public static final int ENEMY = 1 << 1;

    private int collisionLayer = 0;

    /**
     * @param bit desired collision layers bitwise or'd together, e.g.
     * PLAYER | ENEMY would designate something that is collidable with both
     * the player and enemies.
     */
    CollisionLayer(int collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public int getCollisionLayer() {
        return collisionLayer;
    }

    public boolean collidesWith(CollisionLayer other) {
        return (collisionLayer & other.getCollisionLayer()) != 0;
    }
}
