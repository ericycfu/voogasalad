package authoring.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InteractionKeysController {
	
	private List<String> interaction_keys;
	private Map<String, List<AuthoringObject>> interactionMap;
	
	public InteractionKeysController() {
		interaction_keys = new ArrayList<String>();
		interactionMap = new TreeMap<String, List<AuthoringObject>>();
	}
	
	public void addInteractionKey(String interaction_key) {
		if(!interaction_keys.contains(interaction_key)) interaction_keys.add(interaction_key);
	}
	
	public void addInteractionKey(String interaction_key, AuthoringObject authoring_object) {
		addInteractionKey(interaction_key);
		if(interactionMap.containsKey(interaction_key)) {
			if(!interactionMap.get(interaction_key).contains(authoring_object)) {
				interactionMap.get(interaction_key).add(authoring_object);
			}
		}
		else {
			List<AuthoringObject> authoring_objects = new ArrayList<AuthoringObject>();
			authoring_objects.add(authoring_object);
			interactionMap.put(interaction_key, authoring_objects);
		}
	}
	
	public void removeInteractionKey(String interaction_key) {
		if(interaction_keys.contains(interaction_key)) {
			interaction_keys.remove(interaction_key);
			interactionMap.remove(interaction_key);
		}
	}
	
	public List<String> getInteractionKeys() {
		return interaction_keys;
	}
}