package server.commands;

import game_engine.GameInstance;

public class Chat extends Command{

	public Chat(GameInstance g) {
		super(1, g);
	}

	@Override
	public void act() {
		getGameInstance().addToChat(getArgValue(0),this.getArgString(1));
	}

}
