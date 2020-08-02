package model;

public class GoalExit extends Goal {
    private Dungeon dungeon;

    public GoalExit(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean isComplete() {
        return dungeon.getExit().getStatus();
    }

    @Override
    public String decorateGoalString(String goalString) {
        goalString += createCheckbox() + " Get to the exit\n";
        return goalString;
    }
}
