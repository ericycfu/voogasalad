package authoring.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.view.AuthoringView;
import javafx.scene.layout.Pane;

public class MapEntity extends Pane implements AuthoringView {
	/**
	 * size, background image, locations of various objects
	 */
//	private Map<AuthoringObject, List<DraggableImageView>> locations;
	private Map<AuthoringObject, List<AuthoringObject>> locations;
	private MapSettings mapsettings;
	
	public MapEntity() {
//		locations = new HashMap<AuthoringObject, List<DraggableImageView>>();
		locations = new HashMap<AuthoringObject, List<AuthoringObject>>();
		mapsettings = new MapSettings();
		mapsettings.matchToSize(this);
		mapsettings.setMapByImage(this);
	}
	
//	public MapEntity(MapSettings myMapSettings, Map<AuthoringObject, List<DraggableImageView>> myMapEntityMap) {
//		mapsettings = myMapSettings;
//		locations = myMapEntityMap;
//	}
	
	public MapEntity(Map<AuthoringObject, List<AuthoringObject>> myMapEntityMap, MapSettings myMapSettings) {
		mapsettings = myMapSettings;
		locations = myMapEntityMap;
		addImageToMap(locations, myMapSettings);
		mapsettings.matchToSize(this);
		mapsettings.setMapByImage(this);

	}
	
	private void addImageToMap(Map<AuthoringObject, List<AuthoringObject>> locations, MapSettings myMapSettings) {
		for (AuthoringObject key: locations.keySet()) {
			List<AuthoringObject> objects = locations.get(key);
			
			for (AuthoringObject obj : objects) {
				obj.setDragImage(key.getImagePath(), obj.getX(), obj.getY());
//				obj.setDragImage(key.getImagePath(), locations, myMapSettings, obj.getX(), obj.getY());
				this.getChildren().add(obj.getDragImage());
			}
		}
	}

	public void addToMap(AuthoringObject objBase, AuthoringObject objNew) {
		if (locations.get(objBase) == null) {
			locations.put(objBase, new ArrayList<>());
		}
		locations.get(objBase).add(objNew);
		this.getChildren().add(objNew.getDragImage());
	}
	
	public void removeFromMap(AuthoringObject objBase, AuthoringObject objOld) {
		if (locations.containsKey(objBase)) {
			locations.get(objBase).remove(objOld);
		}
		this.getChildren().remove(objOld.getDragImage());
	}
	
	public String getImagePath() {
		return mapsettings.getImagePath();
	}
	
	public String getName() {
		return mapsettings.getName();
	}
	
	public MapSettings getMapSettings() {
		return mapsettings;
	}
	
	public Map<AuthoringObject, List<AuthoringObject>> getLocations() {
		return locations;
	}
	
}
