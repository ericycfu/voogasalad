package game_player;

import java.util.ArrayList;
import java.util.List;

import game_object.GameObject;
import game_object.GameObjectManager;
import pathfinding.GridMap;
import pathfinding.Pathfinder;
import transform_library.Vector2;

public class SelectedUnitManager {
	private List<GameObject> selectedUnits;
	
	public SelectedUnitManager() {
		selectedUnits = new ArrayList<GameObject>();
	}
	
	public void clear() {
		selectedUnits.clear();
	}
	public void add(GameObject go) {
		selectedUnits.add(go);
	}
	
	public void move(Vector2 target, GameObjectManager gom, GridMap gridmap) {
		for (GameObject go : selectedUnits) {
			go.queueMovement(target, gom, gridmap);
		}
	}
	
	public void takeInteraction(int interactionID) {
		
	}
	
	public List<GameObject> getSelectedUnits(){
		return this.selectedUnits;
	}
}
