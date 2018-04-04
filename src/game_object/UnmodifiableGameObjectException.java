package game_object;

/**
 * 
 * @author Rayan
 * Exception called when user attempts to modify or interact with a gameobject that was meant to be uninteractive
 */

public class UnmodifiableGameObjectException extends Exception {
	
	public UnmodifiableGameObjectException(String message)
	{
		super(message);
	}
	
	public UnmodifiableGameObjectException(Throwable exception)
	{
		super(exception);
	}

}
