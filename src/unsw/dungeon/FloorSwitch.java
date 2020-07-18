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

    // Create a method that detects if a boulder entered its space;
    @Override
    public void onEntityEnter(Entity other) {
        super.onEntityEnter(other);
        switch(other.getTag()){
            case BOULDER:
                this.setStatus(true);
                System.out.println("Switch activated!");
                break;
            default:
                break;
        }        
    }
}
