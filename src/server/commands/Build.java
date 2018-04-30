package server.commands;
/**
 * This command instructs the server to build a new GameObject
 * @author andrew
 */
import game_engine.GameInstance;

public class Build extends Command {

	public Build(GameInstance g) {
		super(5, g);
	}

	@Override
	public void act() {
		
		getGameInstance().executeBuildCommand(getArgValue(0), getArgString(1), getArgValue(2), getArgValue(3), getArgValue(4));
	}

}
