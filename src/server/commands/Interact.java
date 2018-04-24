package server.commands;

import game_engine.GameInstance;

public class Interact extends Command {
	public Interact(GameInstance g) {
		super(3, g);
	}

	@Override
	public void act() {
		getGameInstance().executeCommand(getArgValue(0), getArgValue(1), getArgValue(2));
	}
}
