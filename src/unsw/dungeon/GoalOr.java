package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class GoalOr implements Goal {
    private List<Goal> subgoals;

    GoalOr() {
        subgoals = new ArrayList<Goal>();
    }

    public void addGoal(Goal goal) {
        subgoals.add(goal);
    }

    public boolean isComplete() {
        for (Goal goal : subgoals) {
            if (goal.isComplete()) {
                return true;
            }
        }

        return false;
    }
}
