package game_player;

import java.util.ArrayList;
import java.util.List;

import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.PropertyNotFoundException;
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
		System.out.println("my team: " + top.getOwner() + " target: " + target.getOwner());
		try {
		    String interactionName = top.accessLogic().accessInteractions().getInteraction(interactionID).getName();
			if (top.accessLogic().accessInteractions().getInteraction(interactionID).isBuild()) {
				top.queueInteraction(target, interactionID, gom, new GridMap(1000, 1000), position);
			}
			else {
				for (GameObject go : selectedUnits){
					boolean isInteractionValid = false;
					int goSpecificInteractionID = -1;
					for (Interaction i : go.accessLogic().accessInteractions().getElements()) {
						//System.out.println(i.getCustomFunction(0).getParameterFormat().getParameterValue("Delta"));
						if (i.getName().equals(interactionName)) {
							isInteractionValid = true;
							goSpecificInteractionID = i.getID();
						}
					}
					if (isInteractionValid) {
						try {
							System.out.println(go.accessLogic().accessAttributes().getAttribute("Health"));
							System.out.println(target.accessLogic().accessAttributes().getAttribute("Health"));
						} catch (PropertyNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						go.queueInteraction(target, goSpecificInteractionID, gom, new GridMap(1000, 1000), position);
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
