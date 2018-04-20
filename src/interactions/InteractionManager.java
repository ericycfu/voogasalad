package interactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import conditions.Condition;
import game_engine.ElementManager;
import game_engine.EngineObject;
import game_object.GameObject;

public class InteractionManager extends ElementManager {
	
	Map<Integer, Interaction> interactionIndexMap;
	
	public InteractionManager() {
		interactionIndexMap = new TreeMap<Integer, Interaction>();
	}

	
	public int createInteraction()
	{
		int newID = calculateID();
		Interaction interaction = new Interaction(newID);
		this.addElement(interaction);
		return newID;
	}
	
	public List<Interaction> getElements()
	{
		List<Interaction> interactions = new ArrayList<>();
		
		for(EngineObject eObj : getElementsRaw())
		{
			Interaction gObj = (Interaction) eObj;
			interactions.add(gObj);
		}
		return interactions;
	}
	
	public Interaction getInteraction(int id)
	{
		return (Interaction)(this.get(id));
	}
	
}
