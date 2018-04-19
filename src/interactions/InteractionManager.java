package interactions;

import java.util.Map;
import java.util.TreeMap;

import game_engine.ElementManager;

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
	
}