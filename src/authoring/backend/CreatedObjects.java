package authoring.backend;

import java.util.ArrayList;
import java.util.List;

import observables.Listener;
import observables.Speaker;
import observables.StaticSpeaker;

public class CreatedObjects implements StaticSpeaker {
	
	private static List<AuthoringObject> myAuthoringObjects;
	private static List<Listener> myListeners;
	public CreatedObjects() {
		myAuthoringObjects = new ArrayList<>();
		myListeners = new ArrayList<>();
		addObject(new AuthoringObject());
		addObject(new AuthoringObject());
		System.out.print("In CreatedObjects class");
	}
	
	public static void addObject(AuthoringObject obj) {
		if(myAuthoringObjects.contains(obj)) {
			myAuthoringObjects.set(myAuthoringObjects.indexOf(obj), obj);
		}
		else {
			myAuthoringObjects.add(obj);
		}
		notifyListeners();
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

	public void addListener(Listener l) {
		myListeners.add(l);
	}

	public void removeListener(Listener l) {
		myListeners.remove(l);
		
	}
	
	private static void notifyListeners() {
		for (Listener l: myListeners) {
			l.notify();
		}
	}
}