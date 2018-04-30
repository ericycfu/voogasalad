package server.commands;
/**
 * Instructs the game to move a unit to new coordinates
 * @author andrew
 */
import game_engine.GameInstance;

public class Move extends Command {

	public Move(GameInstance g) {
		super(3, g);
	}

	@Override
	public void act() {
		getGameInstance().executeMovement(getArgValue(0), getArgValue(1), getArgValue(2));
	}

}
