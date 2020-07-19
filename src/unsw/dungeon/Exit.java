package unsw.dungeon;

public class Exit extends AlternatingEntity {
    public Exit(Dungeon dungeon, int x, int y) {
        super(x, y, Tag.EXIT, new CollisionLayer(0), dungeon);
    }

    @Override
    public void onEntityEnter(Entity other) {
        super.onEntityEnter(other);

        switch (other.getTag()) {
            case PLAYER:
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
            case PLAYER:
                setStatus(false);
                break;
            default:
                break;
        }
    }
}
