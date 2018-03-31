package game_object;

public class PropertyNotFoundException extends Exception {

	/**
	 * Exception if user a attempts to interact with a unity property that is non existent
	 */
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
