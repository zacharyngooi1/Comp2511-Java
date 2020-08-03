package model;

public class Door extends AlternatingEntity {
    private int id;

    public Door(Dungeon dungeon, int x, int y, int id) {
        super(x, y, Tag.DOOR, new CollisionLayer(CollisionLayer.PLAYER | CollisionLayer.ENEMY), dungeon);
        this.id = id;
    }

    public int getID() {
        return id;
    }

    /**
     * Try to unlock this door given a key.
     * @param key the key to try with this door.
     * @return whether or not this door was successfully unlocked.
     */
    public boolean tryUnlock(Key key) {
        if (id == key.getID()) {
            setCollisionLayer(new CollisionLayer(0));
            setStatus(true);
            return true;
        }

        return false;
    }
}
