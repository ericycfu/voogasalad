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
		myAuthoringObjects.add(obj);
	}
	
	public static List<AuthoringObject> getAuthoringObjects() {
		return myAuthoringObjects;
	}
	
	public AuthoringObject getObjectByIndex(int index) {
		return myAuthoringObjects.get(index);
	}
	
	public int getSize() {
		return myAuthoringObjects.size();
	}
}