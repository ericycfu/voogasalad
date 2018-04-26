package authoring.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import observables.Listener;
import observables.Speaker;

public class CreatedMaps implements Speaker {
	private List<MapEntity> createdmaps;
	private static List<Listener> listeners;

	public CreatedMaps() {
		createdmaps = new ArrayList<>();
		listeners = new ArrayList<>();
	}
	
	public List<MapEntity> getCreatedMaps() {
		return createdmaps;
	}
	
	public MapEntity makeNewMap() {
		MapEntity newmap = new MapEntity();
		createdmaps.add(newmap);
		return newmap;
	}
	
	public MapEntity getObjectByIndex(int index) {
		return createdmaps.get(index);
	}
	
	public int getSize() {
		return createdmaps.size();
	}
	
	@Override
	public void addListener(Listener l) {
		listeners.add(l);
	}

	@Override
	public void removeListener(Listener l) {
		listeners.remove(l);
	}

	@Override
	public void notifyListeners() {
		for (Listener l: listeners) {
			l.update();
		}
	}
}
