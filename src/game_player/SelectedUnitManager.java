package game_player;

import java.util.ArrayList;
import java.util.List;

import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.UnmodifiableGameObjectException;
import interactions.Interaction;
import pathfinding.GridMap;
import pathfinding.Pathfinder;
import transform_library.Vector2;

public class SelectedUnitManager {
	private List<GameObject> selectedUnits;
	private Team myTeam;
	private int myTeamID;
	
	public SelectedUnitManager(Team team) {
		selectedUnits = new ArrayList<GameObject>();
		myTeam = team;
		//myTeamID = myTeam.getID();
	}
	
	public void clear() {
		selectedUnits.clear();
		System.out.println("this get aclled" );
	}
	
	public void add(GameObject go) {
		//if (go.getOwner().getID()==myTeamID) {
			selectedUnits.add(go);
		//}
	}
	
	public void move(Vector2 target, GameObjectManager gom, GridMap gridmap) {
		for (GameObject go : selectedUnits) {
			go.queueMovement(target, gom, gridmap);
		}
	}
	
	public void takeInteraction(Vector2 position, GameObject target, int interactionID, GameObjectManager gom) {
		GameObject top = selectedUnits.get(0);
		System.out.println("Iam  here instead !");
		try {
		    String interactionName = top.accessLogic().accessInteractions().getInteraction(interactionID).getName();
			if (top.accessLogic().accessInteractions().getInteraction(interactionID).isBuild()) {
				System.out.println("Iam  here instead !");
				//top.queueInteraction(position, target, interactionID, gom);
				top.queueInteraction(target, interactionID, gom, new GridMap(1000, 1000));  // JUST FOR TESTING
			}
			else {
				System.out.println("Iam  here !");
				for (GameObject go : selectedUnits){
					boolean isInteractionValid = false;
					for (Interaction i : go.accessLogic().accessInteractions().getElements()) {
						isInteractionValid = i.getName().equals(interactionName);
					}
					if (isInteractionValid) {
						//go.queueInteraction(position, target, interactionID, gom);
						go.queueInteraction(target, interactionID, gom, new GridMap(1000, 1000));  // JUST FOR TESTING
						System.out.println(go.getName());
					
					}
				}
			}
		} catch (UnmodifiableGameObjectException e) {
			
		}
		
	}
	
	public List<GameObject> getSelectedUnits(){
		return this.selectedUnits;
	}
}
