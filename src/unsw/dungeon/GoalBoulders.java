package unsw.dungeon;

public class GoalBoulders implements Goal {
    private Dungeon dungeon;

    GoalBoulders(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public boolean isComplete() {
        int switchesLeft = 0;

        for (FloorSwitch floorSwitch : dungeon.getFloorSwitches()) {
            if (!floorSwitch.getStatus()) {
                switchesLeft++;
            }
        }

        System.out.println(switchesLeft + " switches left");

        return switchesLeft == 0;
    }
}
