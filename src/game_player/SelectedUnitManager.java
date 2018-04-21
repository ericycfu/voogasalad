package game_player;

import java.util.ArrayList;
import java.util.List;

import game_object.GameObject;
import game_object.GameObjectManager;
import interactions.Interaction;
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
	
	public void move(Vector2 target, GameObjectManager gom) {
		for (GameObject go : selectedUnits) {
			go.queueMovement(target, gom.getElements());
		}
	}
	
	public void takeInteraction(Vector2 position, GameObject target, int interactionID, GameObjectManager gom) {
		GameObject top = selectedUnits.get(0);
		String interactionName = top.accessLogic().accessInteractions().getInteraction(interactionID).getName();
		for (GameObject go : selectedUnits){
			boolean isInteractionValid = false;
			for (Interaction i : go.accessLogic().accessInteractions().getElements()) {
				isInteractionValid = i.getName().equals(interactionName);
			}
			if (isInteractionValid) {
				go.queueInteraction(position, target, interactionID, gom);
			}
		}
		//TO-DO: build interaction - only the top unit does build 
	}
	
	public List<GameObject> getSelectedUnits(){
		return this.selectedUnits;
	}
}
