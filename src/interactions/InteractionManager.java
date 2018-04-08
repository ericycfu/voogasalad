package interactions;

import java.util.Map;
import java.util.TreeMap;

import game_engine.ElementManager;

public class InteractionManager implements ElementManager<Interaction> {
	
	Map<Integer, Interaction> interactionManager;
	
	public InteractionManager()
	{
		interactionManager = new TreeMap<>();
	}

	@Override
	public int addElementToManager(Interaction element) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
