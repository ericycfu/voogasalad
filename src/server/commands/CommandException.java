package server.commands;
/**
 * Denotes an Exception that occurs in executing commands on the server
 * @author andrew
 *
 */
public class CommandException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CommandException (String message, Object ... values) {
        super(String.format(message, values));
    }
    
    /**
     * Create an exception based on a caught exception with a different message.
     */
    public CommandException (Throwable cause, String message, Object ... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public CommandException (Throwable exception) {
        super(exception);
    }
}
