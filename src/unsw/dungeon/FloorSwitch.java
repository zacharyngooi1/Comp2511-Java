package unsw.dungeon;

public class FloorSwitch extends Entity {
    private boolean status;

    public FloorSwitch(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.FLOORSWITCH, false, dungeon);
        this.status = false;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        System.out.println("Switch status = " + status);
        this.status = status;
    }

    @Override
    public void onEntityEnter(Entity other) {
        super.onEntityEnter(other);

        switch (other.getTag()) {
            case BOULDER:
                setStatus(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onEntityExit(Entity other) {
        super.onEntityExit(other);

        switch (other.getTag()) {
            case BOULDER:
                setStatus(false);
                break;
            default:
                break;
        }
    }
}
