package unsw.dungeon;


public class Portal extends Entity{

    private int id;

    public Portal(Dungeon dungeon, int x, int y, int id) {
        super(x, y, Tag.PORTAL, false, dungeon);
        this.id = id;
    }


    public int getID() {
        return this.id;
    }

}
