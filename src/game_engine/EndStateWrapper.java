package game_engine;

/**
 * 
 * @author Rayan
 * Wrapper class created to facilitate game end logic for game player
 */

public class EndStateWrapper {

	public static enum EndState
	{
		WIN, LOSE;
	}
	
	private String message;
	private EndState state;
	
	public EndStateWrapper(String message, EndState state)
	{
		this.state = state;
		this.message = message;
	}

	public String getMessage() 
	{
		return message;
	}

	public EndState getState() 
	{
		return state;
	}
	
	
}
