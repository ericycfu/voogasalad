package server.commands;
/**
 * This command instructs the game to queue a non-build interaction between 2 GameObjects
 * @author andrew
 */
import game_engine.GameInstance;

public class Interact extends Command {
	public Interact(GameInstance g) {
		super(5, g);
	}

	@Override
	public void act() {
		getGameInstance().executeCommand(getArgValue(0), getArgValue(1), getArgValue(2),getArgValue(3),getArgValue(4));
	}
}
