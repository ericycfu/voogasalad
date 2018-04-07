package game_engine;

import java.util.Map;
import java.util.TreeMap;

import game_object.GameObject;

/**
 * 
 * @author Rayan
 * Allows access to the gameobjects in the scene by runnning their update methods.
 * Allows the game engine to restrict access of gameobjects from the game player
 */

public class GameObjectManager {
	
	private Map<Integer, GameObject> objectMap;
	
	public GameObjectManager()
	{
		objectMap = new TreeMap<>();
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 * Adds a gameobject to the manager and assigns an ID to it based on the objects already inside
	 */
	public int addGameObjectToManager(GameObject object)
	{
		int id = 1;
		if(objectMap.isEmpty())
		{
			objectMap.put(id, object);
		}
		else
		{
			id = objectMap.size() + 1;
			objectMap.put(id, object);
		}
		return id;

	}
	
	/**
	 *  This will allow the game player to cycle through all the objects and runs their game loop
	 */
	public void runGameObjectLoop()
	{
		for(Map.Entry<Integer, GameObject> entry: objectMap.entrySet())
		{
			entry.getValue().Update();
		}
	}
	

}
