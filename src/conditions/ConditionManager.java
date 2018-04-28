package conditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_engine.ElementManager;
import game_engine.EngineObject;
import game_object.GameObject;

public class ConditionManager extends ElementManager {

	
	public ConditionManager()
	{
		super();
	}
	
//	public int createCondition(GameObject object, int comparatorID, String var1, String var2)
	public int createCondition(int comparatorID, String var1, String var2)
	{
		int newID = calculateID();
//		Condition condition = new Condition(newID, object, comparatorID, var1, var2);
		Condition condition = new Condition(newID, comparatorID, var1, var2);
		this.addElement(condition);
		return newID;
	}
	
	public List<Condition> getElements()
	{
		List<Condition> conditions = new ArrayList<>();
		
		for(EngineObject eObj : getElementsRaw())
		{
			Condition gObj = (Condition) eObj;
			conditions.add(gObj);
		}
		return conditions;
	}
	
	public Condition getCondition(int id)
	{
		return (Condition)(this.get(id));
	}
		
	public List<String> availableCustomConditions() {
		List<String> customConditions = new ArrayList<>();
		customConditions.add(new Death().getClass().getSimpleName());
		return customConditions;
	}
}
