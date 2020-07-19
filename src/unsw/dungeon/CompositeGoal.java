package unsw.dungeon;

import java.util.ArrayList;

// child class of goal that also implements interface method
public class CompositeGoal extends Goal {

    private ArrayList<Goal> subgoals;
    

    public CompositeGoal(Dungeon dungeon, GoalsCompleteInterface implementation) {
        // call super 
        super(dungeon, implementation);
        // create a new list for all subgoals
        this.subgoals = new ArrayList<Goal>();
    }

    public ArrayList<Goal> getSubGoals() {
        return this.subgoals;
    }

    public void addToGoalList(Goal goal) {
        this.subgoals.add(goal);
    }

    public ArrayList<Goal> getRemainingGoals() {
		ArrayList<Goal> result = new ArrayList<Goal>();
        // filter through this subgoals, checking for completion before adding to result
		this.subgoals.stream().filter(d->d.isComplete).forEachOrdered(result::add);

		return result;
	}



}