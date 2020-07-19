package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class GoalAnd implements Goal {
    private List<Goal> subgoals;

    GoalAnd() {
        subgoals = new ArrayList<Goal>();
    }

    public void addGoal(Goal goal) {
        subgoals.add(goal);
    }

    public boolean isComplete() {
        for (Goal goal : subgoals) {
            if (!goal.isComplete()) {
                return false;
            }
        }

        return true;
    }
}
