package model;

public class FloorSwitch extends AlternatingEntity {
    public FloorSwitch(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.FLOORSWITCH, new CollisionLayer(0), dungeon);
    }

    @Override
    public void onEntityEnter(Entity other) {
        super.onEntityEnter(other);

        switch (other.getTag()) {
            case BOULDER:
                setStatus(true);
                dungeon.checkGameWon();
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
                dungeon.checkGameWon();
                break;
            default:
                break;
        }
    }
}
