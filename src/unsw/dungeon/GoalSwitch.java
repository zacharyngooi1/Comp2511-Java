package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads the model (the dungeon, from a .json file) but not the view.
 *
 * It is the responsibility of a subclass to implement the functions that
 * take the model's entities made by this class, create corresponding view/UI
 * elements and then tie the two together.
 *
 * @author Robert Clifton-Everest
 */
public class GoalSwitch{
    // This class will check for different tags for each goal(Not implemented yet)
    // Not sure if enum tags should be here for in Goal class itself
	private GoalsCompleteInterface treasureGoal = new GoalInterfaceTreasure();
	private GoalsCompleteInterface enemyGoal = new GoalInterfaceEnemy();
	private GoalsCompleteInterface switchGoal = new GoalInterfaceSwitch();
	private GoalsCompleteInterface exitGoal  = new GoalInterfaceExit();

    public enum goalTag {
        TREASUREGOAL, ENEMYGOAL, SWITCHESGOAL, EXITGOAL
    }
    private GoalsCompleteInterface goalSwitcher(goalTag tag) {
		switch (tag) {
		case TREASUREGOAL:
            return treasureGoal;
		case ENEMYGOAL:
            return enemyGoal;
		case SWITCHESGOAL:
            return switchGoal;
		case EXITGOAL:
            return exitGoal;
		default:
			break;
		}

}