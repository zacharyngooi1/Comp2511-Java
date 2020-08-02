package model;

public class GoalBoulders extends Goal {
    private Dungeon dungeon;

    public GoalBoulders(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean isComplete() {
        for (FloorSwitch floorSwitch : dungeon.getFloorSwitches()) {
            if (!floorSwitch.getStatus()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String decorateGoalString(String goalString) {
        int switchesLeft = 0;

        for (FloorSwitch floorSwitch : dungeon.getFloorSwitches()) {
            if (!floorSwitch.getStatus()) {
                switchesLeft++;
            }
        }

        goalString += createCheckbox() + " Activate all switches (" + switchesLeft + " left)\n";
        return goalString;
    }
}
