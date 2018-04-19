package authoring.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.Pane;

public class MapEntity extends Pane {
	/**
	 * size, background image, locations of various objects
	 */
	private Map<AuthoringObject, ArrayList<DraggableImageView>> locations;
	private MapSettings mapsettings;
	
	public MapEntity() {
		locations = new HashMap<AuthoringObject, ArrayList<DraggableImageView>>();
		mapsettings = new MapSettings();
	}
	
	public void addToMap(AuthoringObject obj, DraggableImageView dragimg) {
		if (locations.get(obj) == null) 
			locations.put(obj, new ArrayList<DraggableImageView>());
		locations.get(obj).add(dragimg);
	}
}
