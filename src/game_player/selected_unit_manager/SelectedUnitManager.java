package game_player.selected_unit_manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.UnmodifiableGameObjectException;
import interactions.Interaction;
import pathfinding.GridMap;
import transform_library.Vector2;

public abstract class SelectedUnitManager {
	protected List<GameObject> mySelectedUnits;
	private int myTeamID;
	
	/**
	 * Constructs a SelectedUnitManager for the input team.
	 * @param team corresponding team
	 */
	public SelectedUnitManager(Team team) {
		mySelectedUnits = new ArrayList<>();
		myTeamID = team.getID();
	}
	
	/**
	 * Clear all selected units.
	 */
	public void clear() {
		mySelectedUnits.clear();
	}
	
	/**
	 * Add a GameObject to the SelectedUnitManager; This object must be on the same team as the SelectedUnitManager
	 * @param go the target GameObject to be selected
	 */
	public void add(GameObject go) {
		if (go.getOwner().getID()==myTeamID) {
			mySelectedUnits.add(go);
		}
	}
	
	/**
	 * Move all the current selected units to a target location
	 * @param target target location to move to
	 * @param gom the current GameObjectManager, used for path finding
	 * @param gridmap a GridMap whose width and height equals the Map's width and height, used for path finding
	 */
	public abstract void move(Vector2 target, GameObjectManager gom, GridMap gridmap);
	
	/**
	 * Allow all selected units to perform an interaction; checks if the interaction exists for every unit selected
	 * @param location location for the interaction to take place, useful for build
	 * @param target target GameObject for the interaction to act on
	 * @param interactionID the ID of the desired interaction local to the first selected unit
	 * @param gom the current GameObjectManager, used for path finding
	 * @param gridmap a GridMap whose width and height equals the Map's width and height, used for path finding
	 */
	public void takeInteraction(Vector2 location, GameObject target, int interactionID, GameObjectManager gom, GridMap gridmap) {
		GameObject top = mySelectedUnits.get(0);
		try {
			Interaction current = top.accessLogic().accessInteractions().getInteraction(interactionID);
		    String interactionName = current.getName();
			if (current.isBuild()) {
				build(top, target, interactionID, gom, gridmap, location);
			}
			else {
				multipleUnitsTakeInteraction(interactionName, target, gom, gridmap, location);
			}
		} catch (UnmodifiableGameObjectException e) {
			// do nothing
		}
	}
	
	// checks if the specific interaction exists for every selected units
	private void multipleUnitsTakeInteraction(String interactionName, GameObject target, GameObjectManager gom, GridMap gridmap, Vector2 location){
		mySelectedUnits.stream()
			.filter(o -> {
				try {
					List<Interaction> interactionsList = o.accessLogic().accessInteractions().getElements();
					return interactionsList.stream().anyMatch(i -> i.getName().equals(interactionName));
				} catch (UnmodifiableGameObjectException e) {
					return false;
				}
			})   
			.forEach(o -> {
				try {
					List<Interaction> interactionsList = o.accessLogic().accessInteractions().getElements();
					Optional<Interaction> optional = interactionsList.stream().filter(i -> i.getName().equals(interactionName)).findFirst();
					int goSpecificID = optional.get().getID();
					interact(o, target, goSpecificID, gom, gridmap, location);
				} catch (UnmodifiableGameObjectException e) {
					// do nothing
				}
			});
	}
	
	/**
	 * Build a unit at the specific location
	 * @param source the unit performing the build interaction
	 * @param target the unit to be built
	 * @param interactionID ID of the build interaction local to the first selected unit
	 * @param gom the current GameObjectManager, used for path finding
	 * @param gridmap a GridMap whose width and height equals the Map's width and height, used for path finding
	 * @param location the location where the target unit will be built
	 */
	public abstract void build(GameObject source, GameObject target, int interactionID, GameObjectManager gom, GridMap gridmap, Vector2 location);
	
	/**
	 * Perform an interaction by the source to the target
	 * @param source the unit performing the interaction
	 * @param target the unit to be acted upon
	 * @param interactionID ID of the interaction local to the first selected unit
	 * @param gom the current GameObjectManager, used for path finding
	 * @param gridmap a GridMap whose width and height equals the Map's width and height, used for path finding
	 * @param location only used when interaction does not require a target
	 */
	public abstract void interact(GameObject source, GameObject target, int interactionID, GameObjectManager gom, GridMap gridmap, Vector2 location);
	
	/**
	 * Checks if no unit is selected at the moment
	 * @return if no unit is selected 
	 */
	public boolean isEmpty() {
		return mySelectedUnits.isEmpty();
	}
	
	/**
	 * Gets an unmodifiable list of selected units
	 * @return an unmodifiable list of selected units
	 */
	public List<GameObject> getSelectedUnits(){
		return Collections.unmodifiableList(mySelectedUnits);
	}
}
