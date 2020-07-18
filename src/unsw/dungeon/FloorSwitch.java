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

    @Override
    public void onEntityEnter(Entity other) {
        super.onEntityEnter(other);
        switch(other.getTag()){
            case BOULDER:
                System.out.println("Switch Activated!");
                this.setStatus(true);
                System.out.println("switch status: " + getStatus());
                break;
            default:
                break;
        }        
    }

    @Override
    public void onEntityExit(Entity other) {
        super.onEntityExit(other);
        switch(other.getTag()) {
            case BOULDER:
                System.out.println("Switch Deactivated!");
                this.setStatus(false);
                System.out.println("switch status: " + getStatus());
                break;
            default:
                break;
        }
    }
}
