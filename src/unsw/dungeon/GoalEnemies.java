package unsw.dungeon;

public class GoalEnemies implements Goal {
    private Dungeon dungeon;

    GoalEnemies(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public boolean isComplete() {
        return dungeon.getEnemies().size() == 0;
    }
}
