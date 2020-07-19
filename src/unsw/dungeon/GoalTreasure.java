package unsw.dungeon;

public class GoalTreasure implements Goal {
    private Dungeon dungeon;

    GoalTreasure(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public boolean isComplete() {
        System.out.println(dungeon.getTreasures().size() + " treasure(s) remaining");
        return dungeon.getTreasures().size() == 0;
    }
}
