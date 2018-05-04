package authoring.backend;

import java.util.ArrayList;
import java.util.List;

import observables.Listener;
import observables.Speaker;
/**
 * Holds all created AuthoringObjects
 * @author xlany
 *
 */
public class CreatedObjects implements Speaker {
	
	private List<AuthoringObject> myAuthoringObjects;
	private List<Listener> myListeners;
	/**
	 * Constructor initializes necessities
	 */
	public CreatedObjects() {
		myAuthoringObjects = new ArrayList<>();
		myListeners = new ArrayList<>();
	}
	/**
	 * Add
	 * @param obj to list of created objects
	 * Notifies listeners to update
	 */
	public void addObject(AuthoringObject obj) {
		if(myAuthoringObjects.contains(obj)) {
			myAuthoringObjects.set(myAuthoringObjects.indexOf(obj), obj);
		}
		else {
			myAuthoringObjects.add(obj);
		}
		notifyListeners();
	}
	/**
	 * 
	 * @return list of created objects
	 */
	public List<AuthoringObject> getAuthoringObjects() {
		return myAuthoringObjects;
	}
	/**
	 * Resets list of created objects by
	 * @param authoring_objects
	 */
	public void setAuthoringObjects(List<Object> authoring_objects) {
		myAuthoringObjects.clear();
		for(Object obj : authoring_objects) {
			myAuthoringObjects.add((AuthoringObject) obj);
		}
	}
	/**
	 * Get created object from list by
	 * @param index
	 * @return object at index
	 */
	public AuthoringObject getObjectByIndex(int index) {
		return myAuthoringObjects.get(index);
	}
	/**
	 * 
	 * @return number of created objects
	 */
	public int getSize() {
		return myAuthoringObjects.size();
	}
	
	@Override
	public void addListener(Listener l) {
		myListeners.add(l);
	}
	
	@Override
	public void removeListener(Listener l) {
		myListeners.remove(l);
	}
	
	@Override
	public void notifyListeners() {
		for (Listener l: myListeners) {
			l.update();
		}
	}
}