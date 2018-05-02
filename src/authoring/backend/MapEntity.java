package authoring.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.support.DraggableImageView;
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
	
	public MapEntity(MapSettings myMapSettings, Map<AuthoringObject, List<AuthoringObject>> myMapEntityMap) {
		mapsettings = myMapSettings;
		locations = myMapEntityMap;

	}

	public void addToMap(AuthoringObject objBase, AuthoringObject objNew) {
		if (locations.get(objBase) == null) {
			locations.put(objBase, new ArrayList<>());
		}
		locations.get(objBase).add(objNew);
		this.getChildren().add(objNew.getDragImage());
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
