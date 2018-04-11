package interactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_engine.ElementManager;

public class InteractionManager implements ElementManager<Interaction> {
	
	Map<Integer, Interaction> interactionMap;
	
	public InteractionManager()
	{
		interactionMap = new TreeMap<>();
	}

	@Override
	public int addElementToManager(Interaction element) {
		// TODO Auto-generated method stub
		int id = 1;
		if(interactionMap.isEmpty())
		{
			interactionMap.put(id, element);
		}
		else
		{
			id = interactionMap.size() + 1;
			interactionMap.put(id, element);
		}
		return id;
	}

	
	@Override
	public void removeElement(Interaction element) {
		interactionMap.remove(element.getID());
		
	}

	@Override
	public List<Interaction> getElements() {

		List<Interaction> interactionList = new ArrayList<>();
		
		for(Map.Entry<Integer, Interaction> var : interactionMap.entrySet())
		{
			interactionList.add(var.getValue());
		}
		
		return Collections.unmodifiableList(interactionList);
		
	}	

}
