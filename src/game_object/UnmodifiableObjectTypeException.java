package game_object;

public class UnmodifiableObjectTypeException extends Exception {
	
	public UnmodifiableObjectTypeException(String message)
	{
		super(message);
	}
	
	public UnmodifiableObjectTypeException(Throwable exception)
	{
		super(exception);
	}

}
