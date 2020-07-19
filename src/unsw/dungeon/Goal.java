package unsw.dungeon;

public class Goal {

    protected GoalsCompleteInterface implementation;
	protected boolean isComplete;
    protected Dungeon dungeon;

    // implements the interfaces check for completion
    public Goal(Dungeon dungeon, GoalsCompleteInterface implementation) {
		this.dungeon = dungeon;
		this.implementation = implementation;
    }

    public Dungeon getDungeon() {
		return this.dungeon;
	}
    
    // passes this goal into the the implementation of the interface
    public boolean checkGoalCompleted() {
        return this.implementation.isComplete(this);
    }

    // retunrs current status of this goal
    public boolean isComplete() {
        return this.isComplete;
    }

}