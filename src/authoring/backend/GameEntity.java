package authoring.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import game_engine.ResourceManager;
import transform_library.Vector2;

public class GameEntity {
	private CreatedObjects createdobjects;
	private CreatedMaps createdmaps;
	private ResourceManager myResourceManager;
	public GameEntity() {
		createdobjects = new CreatedObjects();
		createdmaps = new CreatedMaps();
		myResourceManager = new ResourceManager();
	}
	
	public GameEntity(List<Object> myAuthoringObjects, Map<AuthoringObject, List<Vector2>> myMap, MapSettings myMapSettings, ResourceManager myResourceManager) {
		createdobjects = new CreatedObjects();
		createdobjects.setAuthoringObjects(myAuthoringObjects);
		Map<AuthoringObject, List<DraggableImageView>> myMapEntityMap = new HashMap<AuthoringObject, List<DraggableImageView>>();
		int ctr = 0;
		for (Entry<AuthoringObject, List<Vector2>> entry: myMap.entrySet()) {
			ctr += 1;
			System.out.println("counter: " +ctr);
			List<DraggableImageView> myDIVs = new ArrayList<DraggableImageView>();
			for(Vector2 vector:(List<Vector2>) entry.getValue()) {
				System.out.println("Entry size: "+ entry.getValue().size());
				myDIVs.add(new DraggableImageView(entry.getKey(), entry.getKey().getImage(), vector.getX(), vector.getY()));
			}
			myMapEntityMap.put(entry.getKey(), myDIVs);
		}
		MapEntity myMapEntity = new MapEntity(myMapSettings, myMapEntityMap);
		myMapSettings.setMap(myMapEntity);
		createdmaps = new CreatedMaps();
		createdmaps.addMap(myMapEntity);
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