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
	
	private List<WinCondition> winConditions;
	
	public SceneManager(GameObjectManager manager, List<WinCondition> winConditions)
	{
		this.winConditions = new ArrayList<>();
		this.objManager = manager;
	}
	
	public EndStateWrapper isWinConditionFulfilled()
	{
		Iterator<GameObject> iter = objManager.getElements().iterator();
		while(iter.hasNext())
		{
			GameObject ob = iter.next();
			for(WinCondition win : winConditions)
			{
				Team team = ob.getOwner();
				if(win.check(team, objManager.getElements()))
				{
					return new EndStateWrapper(win.getVictoryMessage(team.getTeamName()), 
							EndStateWrapper.EndState.WIN);
				}
			}
		}
		return null;
	}

}
