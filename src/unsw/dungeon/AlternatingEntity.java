package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class AlternatingEntity extends Entity {
    private BooleanProperty status;

    public AlternatingEntity(int x, int y, Tag tag, CollisionLayer collisionLayer, Dungeon dungeon) {
        super(x, y, tag, collisionLayer, dungeon);
        this.status = new SimpleBooleanProperty(false);
    }

    public BooleanProperty status() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status.set(status);
        System.out.println(getTag() + " status = " + status);
    }
}
