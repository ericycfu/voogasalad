package interactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.backend.AuthoringObject;
import game_engine.ElementManager;

public class InteractionManager implements ElementManager<Interaction> {
	
	Map<Integer, Interaction> interactionIndexMap;
	
	public InteractionManager() {
		interactionIndexMap = new TreeMap<Integer, Interaction>();
	}

	@Override
	public int addElementToManager(Interaction element) {
		// TODO Auto-generated method stub
		int id = interactionIndexMap.size() + 1;
		interactionIndexMap.put(id, element);

		return id;
	}

	
	@Override
	public void removeElement(Interaction element) {
		interactionIndexMap.remove(element.getID());
	}

	@Override
	public List<Interaction> getElements() {

		List<Interaction> interactionList = new ArrayList<>();
		
		for(Map.Entry<Integer, Interaction> var : interactionIndexMap.entrySet()) {
			interactionList.add(var.getValue());
		}
		
		return Collections.unmodifiableList(interactionList);
	}	
}