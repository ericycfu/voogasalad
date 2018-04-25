package scenemanager;

import java.util.List;

import game_engine.Team;
import game_object.GameObject;
import interactions.CustomComponentParameterFormat;

/**
 * 
 * @author Rayan
 * Interface for creating win condition modules
 */

public interface WinCondition {

	public boolean check(Team team, List<GameObject> gameObjects);
	public CustomComponentParameterFormat getParameterFormat();
	public void setParameterFormatFields();
	public void setParameters(CustomComponentParameterFormat toFormat);
	public String getName();
	public String getVictoryMessage(String teamName);
}
