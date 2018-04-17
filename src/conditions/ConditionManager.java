package conditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_engine.ElementManager;
import game_object.GameObject;

public class ConditionManager extends ElementManager<Condition> {

	private Map<Integer, Condition> conditionMap;
	
	public ConditionManager()
	{}
}
