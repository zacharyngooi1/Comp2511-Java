package unsw.dungeon;

public class GoalSwitches implements Goal {
    private Dungeon dungeon;

    GoalSwitches(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public boolean isComplete() {
        for (FloorSwitch floorSwitch : dungeon.getFloorSwitches()) {
            if (!floorSwitch.getStatus()) {
                return false;
            }
        }

        return true;
    }
}
