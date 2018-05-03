package scenemanager;

import java.io.Serializable;
import java.util.List;

import game_engine.EndStateWrapper;
import game_engine.Team;
import game_object.GameObject;
import interactions.CustomComponentParameterFormat;

/**
 * 
 * @author Rayan
 * Interface for creating end condition modules
	 */
	
	public interface EndCondition extends Serializable {

	public EndStateWrapper check(List<Team> teams, List<GameObject> gameObjects);
	public CustomComponentParameterFormat getParameterFormat();
	public void setParameterFormatFields();
	public void setParameters(CustomComponentParameterFormat toFormat);
	public String getName();
	public String getVictoryMessage(String teamName);
}
