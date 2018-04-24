package game_player;

import java.util.ArrayList;
import java.util.List;

import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import interactions.Interaction;
import transform_library.Vector2;

public class SelectedUnitManager {
	private List<GameObject> selectedUnits;
	private Team myTeam;
	private int myTeamID;
	
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
	
	public void move(Vector2 target, GameObjectManager gom) {
		for (GameObject go : selectedUnits) {
			go.queueMovement(target, gom);
		}
	}
	
	public void takeInteraction(Vector2 position, GameObject target, int interactionID, GameObjectManager gom) {
		GameObject top = selectedUnits.get(0);
		String interactionName = top.accessLogic().accessInteractions().getInteraction(interactionID).getName();
		if (top.accessLogic().accessInteractions().getInteraction(interactionID).isBuild()) {
			top.queueInteraction(position, target, interactionID, gom);
		}
		else {
			for (GameObject go : selectedUnits){
				boolean isInteractionValid = false;
				for (Interaction i : go.accessLogic().accessInteractions().getElements()) {
					isInteractionValid = i.getName().equals(interactionName);
				}
				if (isInteractionValid) {
					go.queueInteraction(position, target, interactionID, gom);
				}
			}
		}
		
	}
	
	public List<GameObject> getSelectedUnits(){
		return this.selectedUnits;
	}
}
