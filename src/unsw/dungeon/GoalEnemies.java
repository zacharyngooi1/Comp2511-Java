package unsw.dungeon;

public class GoalEnemies implements Goal {
    private Dungeon dungeon;

    GoalEnemies(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public boolean isComplete() {
        System.out.println(dungeon.getEnemies().size() + " enemy(ies) remaining");
        return dungeon.getEnemies().size() == 0;
    }
}
