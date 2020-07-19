package unsw.dungeon;

public class GoalTreasure implements Goal {
    private Dungeon dungeon;

    GoalTreasure(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public boolean isComplete() {
        return dungeon.getTreasures().size() == 0;
    }
}
