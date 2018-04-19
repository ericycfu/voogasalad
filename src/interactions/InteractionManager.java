package interactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_engine.ElementManager;

public class InteractionManager extends ElementManager {
	
	Map<Integer, Interaction> interactionMap;
	
	public InteractionManager()
	{
		interactionMap = new TreeMap<>();
	}
	
	public int createInteraction()
	{
		int newID = calculateID();
		Interaction interaction = new Interaction(newID);
		this.addElement(interaction);
		return newID;
	}
	
}
