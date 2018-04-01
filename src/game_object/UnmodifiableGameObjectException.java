package game_object;

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
