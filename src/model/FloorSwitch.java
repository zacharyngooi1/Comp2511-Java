package model;

import controllers.Audio;

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
                Audio.playSound(Audio.switchOn);
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
                Audio.playSound(Audio.switchOff);
                break;
            default:
                break;
        }
    }
}
