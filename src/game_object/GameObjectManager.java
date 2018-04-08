package game_object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_engine.ElementManager;
import transform_library.Transform;

/**
 * 
 * @author Rayan
 * Allows access to the gameobjects in the scene by runnning their update methods.
 * Allows the game engine to restrict access of gameobjects from the game player
 */

public class GameObjectManager implements ElementManager<GameObject>{
	
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
	public int addElementToManager(GameObject object)
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
	
	/**
	 * 
	 * @return
	 * Returns positional information about each object. Can be used to populate grid for pathfinding
	 */
	public List<Transform> accessGameObjectTransforms()
	{
		List<Transform> transformList = new ArrayList<>();
		for(Map.Entry<Integer, GameObject> entry: objectMap.entrySet())
		{
			transformList.add(entry.getValue().getTransform());
		}
		
		return Collections.unmodifiableList(transformList);
	}
	

}
