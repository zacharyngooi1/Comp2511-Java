package model;

import java.util.ArrayList;
import java.util.List;

public class GoalOr extends Goal {
    private List<Goal> subgoals;

    public GoalOr() {
        subgoals = new ArrayList<Goal>();
    }

    public void addGoal(Goal goal) {
        subgoals.add(goal);
    }

    @Override
    public boolean isComplete() {
        for (Goal goal : subgoals) {
            if (goal.isComplete()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String decorateGoalString(String goalString) {
        goalString += createCheckbox() + " Complete one:\n";

        for (Goal goal : subgoals) {
            goalString += "  ";
            goalString = goal.decorateGoalString(goalString);
        }

        return goalString;
    }
}
