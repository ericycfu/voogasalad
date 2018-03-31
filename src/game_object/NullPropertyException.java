package game_object;

public class NullPropertyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullPropertyException(String message)
	{
		super(message);
	}
	
	public NullPropertyException(Throwable exception)
	{
		super(exception);
	}
}
