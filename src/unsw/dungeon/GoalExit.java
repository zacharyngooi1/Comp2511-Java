package unsw.dungeon;

public class GoalExit implements Goal {
    private Dungeon dungeon;

    GoalExit(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public boolean isComplete() {
        System.out.println("Player at exit = " + dungeon.getExit().getStatus());
        return dungeon.getExit().getStatus();
    }
}
