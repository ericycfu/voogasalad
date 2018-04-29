package conditions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_engine.ElementManager;
import game_engine.EngineObject;
import game_object.GameObject;
import interactions.Interaction;

public class ConditionManager extends ElementManager implements Serializable {


	private static final long serialVersionUID = 1L;

	public ConditionManager()
	{
		super();
	}
	
	public ConditionManager(ConditionManager other)
	{
		super(other);
	}
	
//	public int createCondition(GameObject object, int comparatorID, String var1, String var2)

	public int createCondition(int comparatorID, String var1, String var2)
	{
		int newID = calculateID();
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

	
	public void removeLastAddedCondition()
	{
		List<Condition> conds = getElements();
		this.removeElement(conds.get(conds.size()-1));
	}
	

}
