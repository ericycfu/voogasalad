package server.commands;

import game_engine.GameInstance;

public class Pause extends Command{
	public Pause(GameInstance g) {
		super(0, g);
	}

	@Override
	public void act() {
		getGameInstance().stop();
	}
}
