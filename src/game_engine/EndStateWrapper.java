package game_engine;

/**
 * 
 * @author Rayan
 * Wrapper class created to facilitate game end logic for game player
 */

public class EndStateWrapper {

	public static enum EndState
	{
		WIN, LOSE, CONTINUE;
	}
	
	private String message;
	private EndState state;
	private Team team;
	
	public EndStateWrapper(String message, EndState state, Team team)
	{
		this.state = state;
		this.message = message;
		this.team = team;
	}

	public String getMessage() 
	{
		return message;
	}

	public EndState getState() 
	{
		return state;
	}
	
	public Team getTeam()
	{
		return team;
	}
	
	
}
