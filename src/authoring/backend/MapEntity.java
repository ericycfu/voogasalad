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
	private Map<AuthoringObject, List<DraggableImageView>> locations;
	private MapSettings mapsettings;
	
	public MapEntity() {
		locations = new HashMap<AuthoringObject, List<DraggableImageView>>();
		mapsettings = new MapSettings();
		mapsettings.matchToSize(this);
		mapsettings.setMapByImage(this);
	}
	
	public MapEntity(MapSettings myMapSettings, Map<AuthoringObject, List<DraggableImageView>> myMapEntityMap) {
		mapsettings = myMapSettings;
		locations = myMapEntityMap;
	}

	public void addToMap(AuthoringObject obj, DraggableImageView dragimg) {
		if (locations.get(obj) == null) 
			locations.put(obj, new ArrayList<DraggableImageView>());
		locations.get(obj).add(dragimg);
		this.getChildren().add(dragimg);
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
	
	public Map<AuthoringObject, List<DraggableImageView>> getLocations() {
		return locations;
	}
	
}
