package authoring.backend;

import java.util.ArrayList;
import java.util.List;

import observables.Listener;
import observables.Speaker;
import observables.StaticSpeaker;

public class CreatedObjects implements StaticSpeaker {
	
	private List<AuthoringObject> myAuthoringObjects;
	private List<Listener> myListeners;
	
	public CreatedObjects() {
		myAuthoringObjects = new ArrayList<>();
		myListeners = new ArrayList<>();
//		addObject(new AuthoringObject());
	}
	
	public void addObject(AuthoringObject obj) {
		if(myAuthoringObjects.contains(obj)) {
			myAuthoringObjects.set(myAuthoringObjects.indexOf(obj), obj);
		}
		else {
			myAuthoringObjects.add(obj);
		}
		notifyListeners();
	}
	
	public List<AuthoringObject> getAuthoringObjects() {
		return myAuthoringObjects;
	}
	
	public void setAuthoringObjects(List<Object> authoring_objects) {
		myAuthoringObjects.clear();
		for(Object obj : authoring_objects) {
			myAuthoringObjects.add((AuthoringObject) obj);
		}
	}
	
	public AuthoringObject getObjectByIndex(int index) {
		return myAuthoringObjects.get(index);
	}
	
	public int getSize() {
		return myAuthoringObjects.size();
	}

	public void addListener(Listener l) {
		myListeners.add(l);
	}

	public void removeListener(Listener l) {
		myListeners.remove(l);
		
	}
	
	private void notifyListeners() {
		for (Listener l: myListeners) {
			l.update();
		}
	}
}