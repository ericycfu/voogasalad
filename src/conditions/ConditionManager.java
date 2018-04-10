package conditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_engine.ElementManager;
import game_object.GameObject;

public class ConditionManager implements ElementManager<Condition>{

	private Map<Integer, Condition> conditionMap;
	
	public ConditionManager()
	{
		conditionMap = new TreeMap<>();
	}
	
	@Override
	public int addElementToManager(Condition condition)
	{
		int id = 1;
		if(conditionMap.isEmpty())
		{
			conditionMap.put(id, condition);
		}
		else
		{
			id = conditionMap.size() + 1;
			conditionMap.put(id, condition);
		}
		return id;

	}
	
	@Override
	public void removeElement(Condition element) {
		
		conditionMap.remove(element.getID());
	}
	
	@Override
	public List<Condition> getElements() {
		
		List<Condition> conditionList = new ArrayList<>();
		
		for(Map.Entry<Integer, Condition> var : conditionMap.entrySet())
		{
			conditionList.add(var.getValue());
		}
		
		return Collections.unmodifiableList(conditionList);
	}

}
