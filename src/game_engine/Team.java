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
	//private Map<String, Double> availableResources;
	private ResourceManager resourceManager;
	
	public Team(int id, String teamName, ResourceManager resourceManager)
	{
		this.id = id;
		this.teamName = teamName;
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
