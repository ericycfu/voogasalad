package scenemanager;

/**
 * 
 * @author Rayan
 *
 */

public class NullEndConditionException extends Exception {


	private static final long serialVersionUID = 1L;

	public NullEndConditionException() {
		super();
	}

	public NullEndConditionException(String message, Throwable cause) {
		super(message, cause);
	}

	public NullEndConditionException(String message) {
		super(message);
	}

	public NullEndConditionException(Throwable cause) {
		super(cause);
	}

}
