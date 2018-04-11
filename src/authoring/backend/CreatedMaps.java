package authoring.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreatedMaps {
	private List<MapEntity> createdmaps;
	public CreatedMaps() {
		createdmaps = new ArrayList<>();
	}
	
	public MapEntity makeNewMap() {
		MapEntity newmap = new MapEntity();
		createdmaps.add(newmap);
		return newmap;
	}
}
