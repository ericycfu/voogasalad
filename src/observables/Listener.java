package observables;

/**
 * 
 * @author elizabethshulman, xlany
 *
 * This interface conceptually mirrors Java's built-in Observer class. Its intent
 * is to enable an object to track and respond to changes in another object's behavior.
 */
public interface Listener {
	
	/**
	 * This method dictates what a Listener object must do in order to respond to changes
	 * in its corresponding Speaker's behavior.
	 */
	public void update();
}
