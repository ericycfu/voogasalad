package server.commands;
/**
 * Tells the server that the player will leave the game
 * @author andrew
 */
import game_engine.GameInstance;

public class Leave extends Command {

	public Leave(GameInstance g) {
		super(0, g);
	}

	@Override
	public void act() {
		
	}

}
