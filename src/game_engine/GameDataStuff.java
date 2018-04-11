package game_engine;

import game_data.Reader;
import game_data.Writer;

public class GameDataStuff {
	
	// for gameEngine
	private Reader myReader;
	
	List<GameObject> gameObjects = myReader.read(INITIALIZATION_LOCATION, "gameObject");
	List<LossCondition> lossConditions = myReader.read(INITIALIZATION_LOCATION,"lossCondition");
	List<StartingCondition> startingConditions = myReader.read(INITIALIZATION_LOCATION,"startingCondition");
	
	
	// for gamePlayer
	private Reader myReader;
	List<GameObject> gameObjects = myReader.read(INITIALIZATION_LOCATION, "gameObject");
	
}
