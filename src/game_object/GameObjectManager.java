package game_object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import conditions.Condition;
import game_engine.ElementManager;
import game_engine.EngineObject;
import game_engine.Team;
import map.GridMap;
import map.Pathfinder;
import transform_library.Transform;
import transform_library.Vector2;

/**
 * 
 * @author Rayan
 * Allows access to the gameobjects in the scene by runnning their update methods.
 * Allows the game engine to restrict access of gameobjects from the game player
 */

public class GameObjectManager extends ElementManager {
	
	public GameObjectManager()
	{}
	
	
	public int createGameObject(int id, Vector2 startingPosition, List<String> tags, String name, Team t)
	{
		int newID = calculateID();
		GameObject obj = new GameObject(newID, startingPosition, tags, name, t);
		this.addElement(obj);
		return newID;
	}
	
	public int createGameObject(Transform transform, ObjectLogic logic)
	{
		int newID = calculateID();
		GameObject obj = new GameObject(newID, transform, logic);
		this.addElement(obj);
		return newID;
	}
	
	
	public List<GameObject> getElements()
	{
		List<GameObject> gameObjects = new ArrayList<>();
		
		for(EngineObject eObj : getElementsRaw())
		{
			GameObject gObj = (GameObject) eObj;
			gameObjects.add(gObj);
		}
		return gameObjects;
	}
	
	/**
	 *  This will allow the game player to cycle through all the objects and runs their game loop.
	 *  Also removes dead elements at the start of every cycle.
	 */
	public void runGameObjectLoop()
	{
		Iterator<GameObject> iter = this.getElements().iterator();
		while(iter.hasNext())
		{
			GameObject obj = iter.next();
			if(obj.isDead())
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
	 * @param target
	 * @return
	 * Game player can use this to move a specific game object to a position on the map 
	
	public void queueMovement(GameObject obj, Vector2 target)
	{
		obj.queueMovement(target);
	} */
	
	/**
	 * 
	 * @return
	 * Returns positional information about each object. Can be used to populate grid for pathfinding
	 */
	public List<Transform> accessGameObjectTransforms()
	{
		List<Transform> transformList = new ArrayList<>();
		
		for(EngineObject g : getElements())
		{
			GameObject gObj = (GameObject) g;
			transformList.add(gObj.getTransform());
		}
		
		return Collections.unmodifiableList(transformList);
		
		
	}
<<<<<<< HEAD
	
	@Override
	public List<GameObject> getElements() {
		List<GameObject> gameObjectList = new ArrayList<>();
		for(Map.Entry<Integer, GameObject> var : objectMap.entrySet())
		{
			gameObjectList.add(var.getValue());
		}
		
		return gameObjectList;
	}
	public GameObject get(int id) {
		return objectMap.get(id);
	}
	public void clear() {
		objectMap.clear();
	}

=======

	public GameObject getGameObject(int id)
	{
		return (GameObject)(this.get(id));
	}
	
>>>>>>> dev
}
