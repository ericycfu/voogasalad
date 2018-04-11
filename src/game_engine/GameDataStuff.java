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
	
	// for gameAuthor
	private Reader myReader;
	private Writer myWriter;
	//for writing
	myWriter.writeNoOverwrite(INITIALIZATION_LOCATION, **INSERT GAMEOBJECT ARRAY HERE**);
	myWriter.writeNoOverwrite(INITIALIZATION_LOCATION, **INSERT LOSSCONDITION ARRAY HERE**);
	myWriter.writeNoOverwrite(INITIALIZATION_LOCATION, **INSERT STARTINGCONDITION ARRAY HERE**);
	//for reading it in
	List<GameObject> gameObjects = myReader.read(INITIALIZATION_LOCATION, "gameObject");
	List<LossCondition> lossConditions = myReader.read(INITIALIZATION_LOCATION,"lossCondition");
	List<StartingCondition> startingConditions = myReader.read(INITIALIZATION_LOCATION,"startingCondition");
	
}
