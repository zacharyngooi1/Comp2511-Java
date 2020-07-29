package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class GoalAnd extends Goal {
    private List<Goal> subgoals;

    GoalAnd() {
        subgoals = new ArrayList<Goal>();
    }

    public void addGoal(Goal goal) {
        subgoals.add(goal);
    }

    @Override
    public boolean isComplete() {
        for (Goal goal : subgoals) {
            if (!goal.isComplete()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String decorateGoalString(String goalString) {
        goalString += createCheckbox() + " Complete all of the following:\n";

        for (Goal goal : subgoals) {
            goalString += "  ";
            goalString = goal.decorateGoalString(goalString);
        }

        return goalString;
    }
}
