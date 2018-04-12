package authoring.backend;

import java.util.ArrayList;
import java.util.List;

public class CreatedObjects {
	
	private static List<AuthoringObject> myAuthoringObjects;
	
	public CreatedObjects() {
		myAuthoringObjects = new ArrayList<>();
		addObject(new AuthoringObject());
		addObject(new AuthoringObject());
	}
	
	public static void addObject(AuthoringObject obj) {
		if(myAuthoringObjects.contains(obj)) {
			myAuthoringObjects.set(myAuthoringObjects.indexOf(obj), obj);
		}
		else {
			myAuthoringObjects.add(obj);
		}
	}
	
	public static List<AuthoringObject> getAuthoringObjects() {
		return myAuthoringObjects;
	}
	
	public static void setAuthoringObjects(List<Object> authoring_objects) {
		myAuthoringObjects.clear();
		for(Object obj : authoring_objects) {
			myAuthoringObjects.add((AuthoringObject) obj);
		}
	}
	
	public AuthoringObject getObjectByIndex(int index) {
		return myAuthoringObjects.get(index);
	}
	
	public static int getSize() {
		return myAuthoringObjects.size();
	}
}