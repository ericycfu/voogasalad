package conditions;

/**
 * 
 * @author Rayan
 * Exception thrown if an attempt is made to retrieve a comparator with the wrong id.
 *
 */
public class ComparatorNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ComparatorNotFoundException(String message)
	{
		super(message);
	}
	
	public ComparatorNotFoundException(Throwable exception)
	{
		super(exception);
	}

}
