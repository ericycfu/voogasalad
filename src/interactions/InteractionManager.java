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
	Map<String, List<AuthoringObject>> interactionComponentsMap;
	
	public InteractionManager()
	{
		interactionIndexMap = new TreeMap<Integer, Interaction>();
		interactionComponentsMap = new TreeMap<String, List<AuthoringObject>>();
	}

	@Override
	public int addElementToManager(Interaction element) {
		// TODO Auto-generated method stub
		int id = interactionIndexMap.size() + 1;
		interactionIndexMap.put(id, element);
		interactionComponentsMap.put(element.getName(), new ArrayList<AuthoringObject>());

		return id;
	}

	
	@Override
	public void removeElement(Interaction element) {
		interactionIndexMap.remove(element.getID());
		interactionComponentsMap.remove(element.getName());
	}

	@Override
	public List<Interaction> getElements() {

		List<Interaction> interactionList = new ArrayList<>();
		
		for(Map.Entry<Integer, Interaction> var : interactionIndexMap.entrySet()) {
			interactionList.add(var.getValue());
		}
		
		return Collections.unmodifiableList(interactionList);
	}
	
	public List<AuthoringObject> getInteractionComponents(String name) {
		return interactionComponentsMap.get(name);
	}
}