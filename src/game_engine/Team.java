package game_engine;

import java.util.Map;

/**
 * 
 * @author Rayan
 *
 */

public class Team implements EngineObject {
	
	private int id;
	private String teamName;
	private ResourceManager resourceManager;
	
	public Team(int id, ResourceManager resourceManager)
	{
		this.id = id;
		this.teamName = "Team " + id;
		this.resourceManager = resourceManager;
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public int getID()
	{
		return id;
	}
	

	public ResourceManager getResourceManager() {
		return resourceManager;
	}

	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
	}
}
