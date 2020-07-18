package unsw.dungeon;

public class Key extends Entity {
    private int id;
    
    public Key(Dungeon dungeon, int x, int y, int id) {
        super(x, y, Tag.KEY, false, dungeon);
        this.id = id;
    }

    public int getKeyId() {
        return this.id;
    }
}
