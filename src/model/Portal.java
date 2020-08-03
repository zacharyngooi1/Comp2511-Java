package model;

import controllers.Audio;

public class Portal extends Entity {
    private int id;

    public Portal(Dungeon dungeon, int x, int y, int id) {
        super(x, y, Tag.PORTAL, new CollisionLayer(0), dungeon);
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public void onEntityEnter(Entity other) {
        super.onEntityEnter(other);
        MoveableEntity moveableEntity = (MoveableEntity) other;

        for (Portal portal : dungeon.getPortals()) {
            if (portal != null && portal != this && portal.getID() == getID()) {
                if (moveableEntity.moveTo(portal.getX(), portal.getY(), false)) {                
                    Audio.playSound(Audio.teleport);
                }
                return;
            }
        }
    }
}
