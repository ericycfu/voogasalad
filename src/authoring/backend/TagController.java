package authoring.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TagController {
	
	private List<String> tags;
	private Map<String, List<AuthoringObject>> tagMap;
	
	public TagController() {
		tags = new ArrayList<String>();
		tagMap = new TreeMap<String, List<AuthoringObject>>();
	}
	
	public void addTag(String tag) {
		if(!tags.contains(tag)) tags.add(tag);
	}
	
	public void addTag(String tag, AuthoringObject authoring_object) {
		addTag(tag);
		if(tagMap.containsKey(tag)) {
			if(!tagMap.get(tag).contains(authoring_object)) {
				tagMap.get(tag).add(authoring_object);
			}
		}
		else {
			List<AuthoringObject> authoring_objects = new ArrayList<AuthoringObject>();
			authoring_objects.add(authoring_object);
			tagMap.put(tag, authoring_objects);
		}
	}
	
	public void removeTag(String tag) {
		if(tags.contains(tag)) {
			tags.remove(tag);
			tagMap.remove(tag);
		}
	}
	
	public List<String> getTags() {
		return tags;
	}
	
	public Map<String, List<AuthoringObject>> getTagMap() {
		return tagMap;
	}
}