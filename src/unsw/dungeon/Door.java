package unsw.dungeon;

public class Door extends Entity {
    private int id;

    public Door(Dungeon dungeon, int x, int y, int id) {
        super(x, y, Tag.DOOR, new CollisionLayer(CollisionLayer.PLAYER | CollisionLayer.ENEMY), dungeon);
        this.id = id;
    }

    /**
     * Try to unlock this door given a key.
     * @param key the key to try with this door.
     * @return whether or not this door was successfully unlocked.
     */
    public boolean tryUnlock(Key key) {
        if (id == key.getId()) {
            setCollisionLayer(new CollisionLayer(0));
            System.out.println("Door unlocked, sprite should change");
            return true;
        }

        return false;
    }
}
