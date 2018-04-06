package authoring.backend;

import java.util.ArrayList;
import java.util.List;

public class CreatedObjects {
	private List<AuthoringObject> myAuthoringObjects;
	public CreatedObjects() {
		myAuthoringObjects = new ArrayList<>();
		addObject(new AuthoringObject());
		addObject(new AuthoringObject());
	}
	
	public void addObject(AuthoringObject obj) {
		myAuthoringObjects.add(obj);
	}
	
	public AuthoringObject getObjectByIndex(int index) {
		return myAuthoringObjects.get(index);
	}
	
	public int getSize() {
		return myAuthoringObjects.size();
	}
}
