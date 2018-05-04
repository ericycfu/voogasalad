package authoring.backend;

import java.util.List;
import java.util.Map;

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
	
	public GameEntity(List<Object> myAuthoringObjects, Map<AuthoringObject, List<AuthoringObject>> myMap, MapSettings myMapSettings, ResourceManager myResourceManager) {
		createdobjects = new CreatedObjects();
		createdobjects.setAuthoringObjects(myAuthoringObjects);
//		Map<AuthoringObject, List<DraggableImageView>> myMapEntityMap = new HashMap<AuthoringObject, List<DraggableImageView>>();
//		Map<AuthoringObject, List<AuthoringObject>> myMapEntityMap = new HashMap<AuthoringObject, List<AuthoringObject>>();
//
//		int ctr = 0;
//		for (Entry<AuthoringObject, List<AuthoringObject>> entry: myMap.entrySet()) {
//			ctr += 1;
//			System.out.println("counter: " +ctr);
//			List<DraggableImageView> myDIVs = new ArrayList<DraggableImageView>();
//			for(Vector2 vector:(List<Vector2>) entry.getValue()) {
//				System.out.println("Entry size: "+ entry.getValue().size());
//				myDIVs.add(new DraggableImageView(entry.getKey(), entry.getKey().getImage(), vector.getX(), vector.getY()));
//			}
//			myMapEntityMap.put(entry.getKey(), myDIVs);
//		}
//		MapEntity myMapEntity = new MapEntity(myMapSettings, myMapEntityMap);
//		MapEntity myMapEntity = new MapEntity(myMapSettings, myMap);

//		myMapSettings.setMap(myMapEntity);
		createdmaps = new CreatedMaps();
//		createdmaps.addMap(myMapEntity);
		this.myResourceManager = myResourceManager;
	}
	
	public GameEntity(List<Object> myAuthoringObjects, List<Object> myMaps, List<Object> myMapSettings, ResourceManager myResourceManager) {
		createdobjects = new CreatedObjects();
		createdobjects.setAuthoringObjects(myAuthoringObjects);
		createdmaps = new CreatedMaps();
		createdmaps.setCreatedMaps(myMaps, myMapSettings);
		this.myResourceManager = myResourceManager;

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