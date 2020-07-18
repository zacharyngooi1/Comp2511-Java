package unsw.dungeon;

public class ConsumableEntity extends Entity {
    private int maxValue;
    private int value;

    public ConsumableEntity(Dungeon dungeon, int x, int y, Tag tag, int maxValue) {
        super(x, y, tag, false, dungeon);
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

        System.out.println(getTag() + ".value = " + value);

        if (value <= 0) {
            dungeon.getPlayer().consumableDepleted(getTag());
            destroy();
        }
    }

    @Override
    public void onEntityEnter(Entity other) {
        super.onEntityEnter(other);

        switch (other.getTag()) {
            case PLAYER:
                if (dungeon.getPlayer().hasConsumable(getTag())) {
                    destroy();
                } else {
                    setInvisible();
                }
                break;
            default:
                break;
        }
    }
}
