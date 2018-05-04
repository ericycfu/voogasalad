package server.commands;
/**
 * Adds a message to the chat
 * @author andrew
 */
import game_engine.GameInstance;

public class Chat extends Command{

	public Chat(GameInstance g) {
		super(0, g);
	}

	@Override
	public void act() {
		getGameInstance().addToChat(getArgValue(0),this.getArgString(1));
	}

}
