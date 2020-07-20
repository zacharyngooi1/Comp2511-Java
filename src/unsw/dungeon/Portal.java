package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;
// import java.util.Random; 

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

        List<Portal> connectedPortals = new ArrayList<Portal>();

        for (Portal portal : this.dungeon.getPortals()) {
            if (portal != null && portal != this && portal.getID() == getID()) {
                connectedPortals.add(portal);
            }
        }

        // Portal dest = connectedPortals.get((new Random()).nextInt(connectedPortals.size()));
        Portal dest = connectedPortals.get(0);

        moveableEntity.moveTo(dest.getX(), dest.getY(), false);
    }
}
