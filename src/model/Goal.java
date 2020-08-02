package model;

public abstract class Goal {
    /**
     * @return whether or not this goal is complete.
     */
    public abstract boolean isComplete();

    /**
     * Appends this goal's information onto the given goal string.
     * @return the new goal string.
     */
    public abstract String decorateGoalString(String goalString);

    /**
     * @return the string "[x]" if this goal is complete, or otherwise "[ ]".
     */
    protected String createCheckbox() {
        return "[" + (isComplete() ? "x" : " ") + "]";
    }
}
