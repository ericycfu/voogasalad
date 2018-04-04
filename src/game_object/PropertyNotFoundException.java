package game_object;

/**
 * 
 * @author Rayan
 * Exception if user a attempts to interact with a unit property that is non existent
 */

public class PropertyNotFoundException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public PropertyNotFoundException(String message)
	{
		super(message);
	}
	
	public PropertyNotFoundException(Throwable exception)
	{
		super(exception);
	}
}
