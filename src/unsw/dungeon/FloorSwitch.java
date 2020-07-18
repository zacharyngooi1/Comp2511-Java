package unsw.dungeon;

public class FloorSwitch extends Entity {
    private boolean status = false;
    public FloorSwitch(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.FLOORSWITCH, false, dungeon);
        this.status = false;
    }

    public void setStatus(boolean change) {
        this.status = change;
    }

    public boolean getStatus() {
        return this.status;
    }

}
