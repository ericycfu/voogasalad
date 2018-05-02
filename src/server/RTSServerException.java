package server;

public class RTSServerException extends RuntimeException {

	/**
	 * a class Exception for Server- or Client-specific errors
	 * @author andrew
	 */
	private static final long serialVersionUID = 1L;
	public RTSServerException (String message, Object ... values) {
        super(String.format(message, values));
    }
    
    /**
     * Create an exception based on a caught exception with a different message.
     */
    public RTSServerException (Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public RTSServerException (Throwable exception) {
        super(exception);
    }
}
