package server.commands;
/**
 * Instructs the game to resume
 * @author andrew
 */
import game_engine.GameInstance;

public class Play extends Command{
	public Play(GameInstance g) {
		super(0, g);
	}

	@Override
	public void act() {
		getGameInstance().play();
	}
}
