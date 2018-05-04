package observables;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author elizabethshulman, xlany
 *
 * Modeled after Java's built-in Observable class, Speaker enables an object to register Listeners to
 * track changes in its behavior.
 */
public interface Speaker {
	
	public  List<Listener> myListeners = new ArrayList<>();
	
	/**
	 * Adds a Listener object to myListeners, enabling it to receive notification
	 * of changes to this object's behavior
	 * @param l	 Listener to register as an observer of this object's behavior
	 */
	public void addListener(Listener l);

	/**
	 * Removes a Listener from this Object's list of respondees to changes in its behavior.
	 * @param l	 Listener to be removed
	 */
	public void removeListener(Listener l);

	/**
	 * Specifies what a Speaker object should do in order to communicate to Listeners that
	 * its behavior has changed, and in order to cue the appropriate reaction in Listeners
	 */
	public void notifyListeners();

}
