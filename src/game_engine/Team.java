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
	
	/*
	public boolean checkEnoughResources(Map<String, Double> costs) {
		for(String s: costs.keySet()) {
			Double currentAvailable = availableResources.get(s);
			if(currentAvailable  == null || currentAvailable < costs.get(s))
				return false;
		}
		return true;
	}
	public void changeResource(String varName, double delta) {
		if(availableResources.get(varName) == null) {
			if(delta > 0)
				availableResources.put(varName, delta);
		}
		else availableResources.put(varName, availableResources.get(varName) + delta);
	}
	public void changeMultipleResources(Map<String,Double> changes) {
		for(String s: changes.keySet())
			changeResource(s, changes.get(s));
	}*/
}
