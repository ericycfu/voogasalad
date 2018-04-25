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
		GameObject newGO = getGameInstance().getGameInfo().get(getArgString(1));
		newGO.setTransform(new Transform(new Vector2(getArgValue(2),getArgValue(3))));
		getGameInstance().getGameObjects().getGameObject(getArgValue(0)).queueInteraction(newGO, getArgValue(4), getGameInstance().getGameObjects());
	}

}
