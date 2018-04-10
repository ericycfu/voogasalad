package game_object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
	@Override
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
	
	@Override
	public void removeElement(GameObject element) {
		
		objectMap.remove(element.getID());
	}
	
	
	/**
	 *  This will allow the game player to cycle through all the objects and runs their game loop.
	 *  Also removes dead elements at the start of every cycle.
	 */
	public void runGameObjectLoop()
	{
		Iterator<Map.Entry<Integer, GameObject>> iter = objectMap.entrySet().iterator();
		while(iter.hasNext())
		{
			Map.Entry<Integer, GameObject> entry = iter.next();
			if(entry.getValue().isDead())
			{
				iter.remove();
			}
		}
		
		for(GameObject obj : getElements())
		{
			obj.Update();
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
		
		for(GameObject g : getElements())
		{
			transformList.add(g.getTransform());
		}
		
		return Collections.unmodifiableList(transformList);
		
		
	}

	
	@Override
	public List<GameObject> getElements() {
		List<GameObject> gameObjectList = new ArrayList<>();
		for(Map.Entry<Integer, GameObject> var : objectMap.entrySet())
		{
			gameObjectList.add(var.getValue());
		}
		
		return Collections.unmodifiableList(gameObjectList);
	}
	public GameObject get(int id) {
		return objectMap.get(id);
	}
	

}
