package authoring.backend;

import java.util.ArrayList;
import java.util.List;

public class CreatedObjects {
	private List<AuthoringObject> myAuthoringObjects;
	public CreatedObjects() {
		myAuthoringObjects = new ArrayList<>();
		addObject(new AuthoringObject());
	}
	
	public void addObject(AuthoringObject obj) {
		myAuthoringObjects.add(obj);
	}
}
