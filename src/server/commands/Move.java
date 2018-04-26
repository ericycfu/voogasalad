package server.commands;

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
