package game_player.selected_unit_manager;

import java.util.ArrayList;
import java.util.List;

import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.UnmodifiableGameObjectException;
import pathfinding.GridMap;
import transform_library.Vector2;

public abstract class SelectedUnitManager {
	protected List<GameObject> selectedUnits;
	protected Team myTeam;
	protected int myTeamID;
	
	public SelectedUnitManager(Team team) {
		selectedUnits = new ArrayList<GameObject>();
		myTeam = team;
		myTeamID = myTeam.getID();
	}
	
	public void clear() {
		selectedUnits.clear();
	}
	
	public void add(GameObject go) {
		if (go.getOwner().getID()==myTeamID) {
			selectedUnits.add(go);
		}
	}
	
	public abstract void move(Vector2 target, GameObjectManager gom, GridMap gridmap);
	
	public void takeInteraction(Vector2 position, GameObject target, int interactionID, GameObjectManager gom, GridMap gridmap) {
		GameObject top = selectedUnits.get(0);
		try {
		    String interactionName = top.accessLogic().accessInteractions().getInteraction(interactionID).getName();
			if (top.accessLogic().accessInteractions().getInteraction(interactionID).isBuild()) {
				build(top, target, interactionID, gom, gridmap, position);
			}
			else {
				selectedUnits.stream()
					.filter(o -> {
						try {
							return o.accessLogic().accessInteractions().getElements().stream()
														.anyMatch(i -> i.getName().equals(interactionName));
						} catch (UnmodifiableGameObjectException e) {
							return false;
						}
					})   
					.forEach(o -> {
						try {
							int goSpecificID = o.accessLogic().accessInteractions().getElements().stream()
													.filter(i -> i.getName().equals(interactionName)).findFirst().get().getID();
							interact(o, target, goSpecificID, gom, gridmap, position);
						} catch (UnmodifiableGameObjectException e) {
							// do nothing
						}
					});
			}
		} catch (UnmodifiableGameObjectException e) {
			// do nothing
		}
	}
	
	public abstract void build(GameObject source, GameObject target, int interactionID, GameObjectManager gom, GridMap gridmap, Vector2 position);
	
	public abstract void interact(GameObject source, GameObject target, int interactionID, GameObjectManager gom, GridMap gridmap, Vector2 position);
	
	public List<GameObject> getSelectedUnits(){
		return selectedUnits;
	}
}
