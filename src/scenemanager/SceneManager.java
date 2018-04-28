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
	private List<Team> teams;
	
	public SceneManager(List<Team> teams, GameObjectManager manager, List<EndCondition> endConditions)
	{
		this.endConditions = new ArrayList<>();
		this.objManager = manager;
		this.teams = teams;
	}
	
	
	public EndStateWrapper checkEndCondition() throws NullEndConditionException
	{	
		for(EndCondition end: endConditions)
		{
			EndStateWrapper endState = end.check(teams, objManager.getElements());
			if(!endState.getState().equals(EndStateWrapper.EndState.CONTINUE))
				return endState;
		}
		
		throw new NullEndConditionException("No End Conditions have been made for your game");
	}

}
