package unsw.dungeon;

public class FloorSwitch extends Entity {
    private boolean status;

    public FloorSwitch(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.FLOORSWITCH, new CollisionLayer(0), dungeon);
        this.status = false;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
        System.out.println("Switch status = " + status + ", sprite should reflect this");
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
