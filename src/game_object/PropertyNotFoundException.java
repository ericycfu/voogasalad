package game_object;

public class PropertyNotFoundException extends Exception {

	/**
	 * 
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
