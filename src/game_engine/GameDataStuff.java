package game_engine;

import java.io.IOException;
import java.util.ArrayList;

import game_data.Reader;
import game_data.Writer;
import game_object.GameObjectManager;
import game_object.ObjectLogic;
import resources.Resources;
import transform_library.Transform;
import transform_library.Vector2;


//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import authoring.backend.AuthoringObject;
//import game_data.Reader;
//import game_data.Writer;
//import game_object.GameObject;
//import transform_library.Transform;
//import transform_library.Vector2;
//
public class GameDataStuff {
	public static void main(String[] args) throws IOException {
		GameObjectManager GOM= new GameObjectManager();
		GOM.createGameObject(new Transform(new Vector2()), new ObjectLogic());
		Reader myReader = new Reader();
		Writer myWriter = new Writer();
		myWriter.write(Resources.getString("TEST_LOCATION"), new ArrayList<GameObjectManager>() {{
			add(GOM);
		}}
		);
	}
}
////	private Reader myReader;
////	
////	List<GameObject> gameObjects = myReader.read(INITIALIZATION_LOCATION, "gameObject");
////	List<LossCondition> lossConditions = myReader.read(INITIALIZATION_LOCATION,"lossCondition");
////	List<StartingCondition> startingConditions = myReader.read(INITIALIZATION_LOCATION,"startingCondition");
////	
////	
////	// for gamePlayer
////	private Reader myReader;
////	List<GameObject> gameObjects = myReader.read(INITIALIZATION_LOCATION, "gameObject");
////	
////	// for gameAuthor
////	private Reader myReader;
////	private Writer myWriter;
////	//for writing
////	myWriter.writeNoOverwrite(INITIALIZATION_LOCATION, **INSERT GAMEOBJECT ARRAY HERE**);
////	myWriter.writeNoOverwrite(INITIALIZATION_LOCATION, **INSERT LOSSCONDITION ARRAY HERE**);
////	myWriter.writeNoOverwrite(INITIALIZATION_LOCATION, **INSERT STARTINGCONDITION ARRAY HERE**);
////	//for reading it in
////	List<GameObject> gameObjects = myReader.read(INITIALIZATION_LOCATION, "gameObject");
////	List<LossCondition> lossConditions = myReader.read(INITIALIZATION_LOCATION,"lossCondition");
////	List<StartingCondition> startingConditions = myReader.read(INITIALIZATION_LOCATION,"startingCondition");
//	
//	//actual gameAuthor code
//	
//	//load
//	myReader.read(AUTHOR LOCATION);
//	
//	//save
//	myWriter.write(AUTHOR LOCATION, List including CreatedObjects object and the map)
//	
//	//run game
//	save()
//	List<GameObject> gameObjects = convert(map);
//	myWriter.write(INITIALIZATION_LOCATION, gameObjects);
//	
//	//convert map to list of gameObjects
//	public List<GameObject> transform(Map<AuthoringObject, Vector2> map){
//		List<GameObject> gameObjects = new ArrayList<GameObject>();
//		for(AuthoringObject authorObj: map.keySet()) {
//			GameObject gameObj = new GameObject(new Transform(map.get(authorObj)), authorObj.getObjectLogic());
//			gameObjects.add(gameObj);
//		}
//		return gameObjects;
//	}
//	
//}
