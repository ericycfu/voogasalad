package authoring.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagController {
	
	private List<String> tags;
	private Map<String, List<AuthoringObject>> tagMap;
	
	public TagController() {
		tags = new ArrayList<String>();
		tagMap = new HashMap<String, List<AuthoringObject>>();
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
	
	public void updateTag(int index, String new_tag) {
		String old_tag = tags.get(index);
		if(tagMap.containsKey(old_tag)) {
			List<AuthoringObject> authoring_objects = tagMap.get(old_tag);
			tagMap.remove(old_tag);
			tagMap.put(new_tag, authoring_objects);
			for(AuthoringObject ao : authoring_objects) {
				ao.getTags().set(ao.getTags().indexOf(old_tag), new_tag);
			}
		}
		tags.set(index, new_tag);
	}
		
	public List<String> getTags() {
		return tags;
	}
	
	public Map<String, List<AuthoringObject>> getTagMap() {
		return tagMap;
	}
}