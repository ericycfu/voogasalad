package authoring.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteractionTagsController {
	
	private Map<AuthoringObject, Map<Integer, List<String>>> interactionTagsMap;
	
	public InteractionTagsController() {
		interactionTagsMap = new HashMap<AuthoringObject, Map<Integer, List<String>>>();
	}
	
	public void addInteractionTag(AuthoringObject authoring_object, int interaction_id, String interaction_tag) {
		Map<Integer, List<String>> nestedMap;
		if(interactionTagsMap.containsKey(authoring_object)) {
			nestedMap = interactionTagsMap.get(authoring_object);
			if(nestedMap.containsKey(interaction_id)) {
				if(!nestedMap.get(interaction_id).contains(interaction_tag)) {
					nestedMap.get(interaction_id).add(interaction_tag);
				}
			}
			else {
				nestedMap.put(interaction_id, new ArrayList<String>());
				nestedMap.get(interaction_id).add(interaction_tag);
			}
		}
		else {
			interactionTagsMap.put(authoring_object, new HashMap<Integer, List<String>>());
			nestedMap = interactionTagsMap.get(authoring_object);
			nestedMap.put(interaction_id, new ArrayList<String>());
			nestedMap.get(interaction_id).add(interaction_tag);
		}
	}
	
	public void removeInteractionTag(AuthoringObject authoring_object, int interaction_id, String interaction_tag) {
		if(interactionTagsMap.containsKey(authoring_object)) {
			if(interactionTagsMap.get(authoring_object).containsKey(interaction_id)) {
				if(interactionTagsMap.get(authoring_object).get(interaction_id).contains(interaction_tag)) {
					interactionTagsMap.get(authoring_object).get(interaction_id).remove(interaction_tag);
				}
			}
		}
	}
	
	public List<String> getInteractionTags(AuthoringObject authoring_object, int interaction_id) {
		return interactionTagsMap.get(authoring_object).get(interaction_id);
	}
}