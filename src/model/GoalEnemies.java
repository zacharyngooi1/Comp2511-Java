package model;

public class GoalEnemies extends Goal {
    private Dungeon dungeon;

    public GoalEnemies(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean isComplete() {
        return dungeon.getEnemies().size() == 0;
    }

    @Override
    public String decorateGoalString(String goalString) {
        goalString += createCheckbox() + " Kill all enemies (" + dungeon.getEnemies().size() + " left)\n";
        return goalString;
    }
}
