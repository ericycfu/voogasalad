package conditions;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_engine.ElementManager;

public class ConditionManager implements ElementManager<Condition>{

	private Map<Integer, Condition> conditionMap;
	
	public ConditionManager()
	{
		conditionMap = new TreeMap<>();
	}
	
	@Override
	public int addElementToManager(Condition element) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeElement(Condition element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Condition> getElements() {
		// TODO Auto-generated method stub
		return null;
	}

}
