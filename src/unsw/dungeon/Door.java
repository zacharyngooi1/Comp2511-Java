package unsw.dungeon;

public class Door extends Entity {
    private int id;

    public Door(Dungeon dungeon, int x, int y, int id) {
        super(x, y, Tag.DOOR, true, dungeon);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Try to unlock this door given a key.
     * @param key the key to try with this door.
     * @return whether or not this door was successfully unlocked.
     */
    public boolean tryUnlock(Key key) {
        if (id == key.getId()) {
            System.out.println("Door unlocked");
            setCollidable(false); // And change sprite
            return true;
        }

        return false;
    }
}
