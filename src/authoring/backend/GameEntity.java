package authoring.backend;

public class GameEntity {
	private CreatedObjects createdobjects;
	private CreatedMaps createdmaps;
	public GameEntity() {
		createdobjects = new CreatedObjects();
		createdmaps = new CreatedMaps();
	}
	
	public CreatedObjects getCreatedObjects() {
		return createdobjects;
	}
	
	public CreatedMaps getCreatedMaps() {
		return createdmaps;
	}
}

