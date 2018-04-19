package conditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_engine.ElementManager;
import game_object.GameObject;
import interactions.Interaction;

public class ConditionManager extends ElementManager {

	
	public ConditionManager()
	{}
	
	public int createCondition(GameObject object, int comparatorID, String var1, String var2)
	{
		int newID = calculateID();
		Condition condition = new Condition(newID, object, comparatorID, var1, var2);
		this.addElement(condition);
		return newID;
	}
}
