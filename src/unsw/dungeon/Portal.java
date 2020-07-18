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

    @Override
    public void onEntityEnter(Entity other) {
        MoveableEntity object = (MoveableEntity) other;
        if (object.getTag() != Tag.PORTAL) {
            super.onEntityEnter(other);
            for (Portal p: this.dungeon.getPortalList()) {
                if (p != null) {
                    if (p.getID() == this.getID() && this.getX() != p.getX() && this.getY() != p.getY()) {
                        object.moveTo(p.getX() +1, p.getY());
                        break;
                    }
                }
            }  
        }
    }


}
