package model;

public class GoalTreasure extends Goal {
    private Dungeon dungeon;

    public GoalTreasure(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean isComplete() {
        return dungeon.getTreasures().size() == 0;
    }

    @Override
    public String decorateGoalString(String goalString) {
        goalString += createCheckbox() + " Collect all treasure (" + dungeon.getTreasures().size() + " left)\n";
        return goalString;
    }
}
