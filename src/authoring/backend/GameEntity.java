package authoring.backend;

import game_engine.ResourceManager;

public class GameEntity {
	private CreatedObjects createdobjects;
	private CreatedMaps createdmaps;
	private ResourceManager myResourceManager;
	public GameEntity() {
		createdobjects = new CreatedObjects();
		createdmaps = new CreatedMaps();
		myResourceManager = new ResourceManager();
	}
	
	public CreatedObjects getCreatedObjects() {
		return createdobjects;
	}
	
	public CreatedMaps getCreatedMaps() {
		return createdmaps;
	}
	public ResourceManager getResourceManager() {
		return myResourceManager;
	}
}

