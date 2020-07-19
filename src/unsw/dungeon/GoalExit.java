package unsw.dungeon;

public class GoalExit implements Goal {
    private Dungeon dungeon;

    GoalExit(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public boolean isComplete() {
        return dungeon.getExit().getStatus();
    }
}
