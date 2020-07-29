package unsw.dungeon;

public class ConsumableEntity extends Entity {
    private int maxValue;
    private int value;

    public ConsumableEntity(Dungeon dungeon, int x, int y, Tag tag, int maxValue) {
        super(x, y, tag, new CollisionLayer(0), dungeon);
        this.maxValue = maxValue;
        value = maxValue;
    }

    public int getValue() {
        return value;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setValue(int value) {
        this.value = value;

        if (value <= 0) {
            dungeon.getPlayer().consumableDepleted(getTag());
        }
    }

    @Override
    public void onEntityEnter(Entity other) {
        super.onEntityEnter(other);

        switch (other.getTag()) {
            case PLAYER:
                removeFromDungeon();
                break;
            default:
                break;
        }
    }
}
