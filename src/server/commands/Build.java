package server.commands;

import game_engine.GameInstance;
import game_object.GameObject;
import transform_library.Transform;
import transform_library.Vector2;

public class Build extends Command {

	public Build(GameInstance g) {
		super(5, g);
	}

	@Override
	public void act() {
		
		getGameInstance().executeBuildCommand(getArgValue(0), getArgString(1), getArgValue(2), getArgValue(3), getArgValue(4));
	}

}
