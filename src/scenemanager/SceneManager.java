package scenemanager;

import java.util.ArrayList;
import java.util.List;

import game_engine.ResourceManager;
import game_engine.Team;
import game_object.GameObjectManager;

/**
 * 
 * @author Rayan
 * The Scene Manager checks for win conditions.
 */

public class SceneManager {
 
	private ResourceManager rscManager;
	private Team team;
	private GameObjectManager objManager;
	
	private List<WinCondition> winConditions;
	
	public SceneManager()
	{
		winConditions = new ArrayList<>();
	}
}
