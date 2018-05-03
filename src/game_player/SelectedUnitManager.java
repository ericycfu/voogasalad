package game_player;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.UnmodifiableGameObjectException;
import pathfinding.GridMap;
import transform_library.Vector2;

public class SelectedUnitManager {
	private List<GameObject> selectedUnits;
	private Team myTeam;
	private int myTeamID;
	private Socket mySocket;
	
	public SelectedUnitManager(Team team, Socket socket) {
		selectedUnits = new ArrayList<GameObject>();
		myTeam = team;
		mySocket = socket;
		//myTeamID = myTeam.getID();
	}
	
	public void clear() {
		selectedUnits.clear();
	}
	
	public void add(GameObject go) {
		//if (go.getOwner().getID()==myTeamID) {
			selectedUnits.add(go);
		//}
	}
	
	public void move(Vector2 target, GameObjectManager gom, GridMap gridmap) {
		selectedUnits.stream()
				.filter(o -> !o.getTransform().getPosition().matches(target))
				.forEach(o -> o.queueMovement(target, gom, gridmap));
		/**
		for (GameObject go : selectedUnits) {
			if (!target.matches(go.getTransform().getPosition())) {
				go.queueMovement(target, gom, gridmap);
				
				ObjectOutputStream outstream = GamePlayer.getObjectOutputStream(mySocket);
				String msg = "Move " + go.getID() + GamePlayer.SPACE + target.getX() + GamePlayer.SPACE + target.getY();
				try {
					outstream.writeObject(msg);
					outstream.flush();
				} catch (IOException e) {
					// do nothing
				}

			}
		}
		**/
	}
	
	public void takeInteraction(Vector2 position, GameObject target, int interactionID, GameObjectManager gom, GridMap gridmap) {
		GameObject top = selectedUnits.get(0);
		try {
		    String interactionName = top.accessLogic().accessInteractions().getInteraction(interactionID).getName();
			if (top.accessLogic().accessInteractions().getInteraction(interactionID).isBuild()) {
				top.queueInteraction(target, interactionID, gom, new GridMap(1000, 1000), position);
				/**
				ObjectOutputStream outstream = GamePlayer.getObjectOutputStream(mySocket);
				String msg = "Build " + top.getID() + GamePlayer.SPACE + target.getName() + GamePlayer.SPACE + interactionID + GamePlayer.SPACE + position.getX() + GamePlayer.SPACE + position.getY();
				try {
					outstream.writeObject(msg);
					outstream.flush();
				} catch (IOException e) {
					// do nothing
				}
				**/
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
							o.queueInteraction(target, goSpecificID, gom, gridmap, position);
						} catch (UnmodifiableGameObjectException e) {
							// do nothing
						}
					});
			}
			/**
				for (GameObject go : selectedUnits){
						ObjectOutputStream outstream = GamePlayer.getObjectOutputStream(mySocket);
						String msg = "Interact " + go.getID() + GamePlayer.SPACE + target.getID() + GamePlayer.SPACE + goSpecificInteractionID + GamePlayer.SPACE + position.getX() + GamePlayer.SPACE + position.getY();
						try {
							outstream.writeObject(msg);
							outstream.flush();
						} catch (IOException e) {
							// do nothing
						}
					}
				}
			}
			**/
		} catch (UnmodifiableGameObjectException e) {
			// do nothing
		}
	}
	
	public List<GameObject> getSelectedUnits(){
		return selectedUnits;
	}
}
