package scenemanager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game_engine.EndStateWrapper;
import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;

/**
 * 
 * @author Rayan
 * The Scene Manager checks for win conditions.
 */

public class SceneManager {
 
	private GameObjectManager objManager;
	
	private List<EndCondition> endConditions;
	
	public SceneManager(GameObjectManager manager, List<EndCondition> winConditions)
	{
		this.endConditions = new ArrayList<>();
		this.objManager = manager;
	}
	
	public EndStateWrapper checkEndCondition()
	{
		Iterator<GameObject> iter = objManager.getElements().iterator();
		while(iter.hasNext())
		{
			GameObject ob = iter.next();
			for(EndCondition end : endConditions)
			{
				Team team = ob.getOwner();
				return end.check(team, objManager.getElements());
			}
		}
		return null;
	}

}
