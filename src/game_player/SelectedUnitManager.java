package game_player;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.UnmodifiableGameObjectException;
import interactions.Interaction;
import pathfinding.GridMap;
import transform_library.Vector2;

public class SelectedUnitManager {
	private static final String SPACE = " ";
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
		for (GameObject go : selectedUnits) {
			if (!target.matches(go.getTransform().getPosition())) {
				go.queueMovement(target, gom, gridmap);
				/**
				ObjectOutputStream outstream = GamePlayer.getObjectOutputStream(mySocket);
				String msg = "Move " + go.getID() + SPACE + target.getX() + SPACE + target.getY();
				try {
					outstream.writeObject(msg);
					outstream.flush();
				} catch (IOException e) {
					// do nothing
				}
				**/
			}
		}
	}
	
	public void takeInteraction(Vector2 position, GameObject target, int interactionID, GameObjectManager gom) {
		GameObject top = selectedUnits.get(0);
		try {
		    String interactionName = top.accessLogic().accessInteractions().getInteraction(interactionID).getName();
			if (top.accessLogic().accessInteractions().getInteraction(interactionID).isBuild()) {
				top.queueInteraction(target, interactionID, gom, new GridMap(1000, 1000), position);
				/**
				ObjectOutputStream outstream = GamePlayer.getObjectOutputStream(mySocket);
				String msg = "Build " + top.getID() + SPACE + target.getName() + SPACE + interactionID + SPACE + position.getX() + SPACE + position.getY();
				try {
					outstream.writeObject(msg);
					outstream.flush();
				} catch (IOException e) {
					// do nothing
				}
				**/
			}
			else {
				for (GameObject go : selectedUnits){
					boolean isInteractionValid = false;
					int goSpecificInteractionID = -1;
					for (Interaction i : go.accessLogic().accessInteractions().getElements()) {
						if (i.getName().equals(interactionName)) {
							isInteractionValid = true;
							goSpecificInteractionID = i.getID();
						}
					}
					if (isInteractionValid) {
						go.queueInteraction(target, goSpecificInteractionID, gom, new GridMap(1000, 1000), position);
						/**
						ObjectOutputStream outstream = GamePlayer.getObjectOutputStream(mySocket);
						String msg = "Interact " + go.getID() + SPACE + target.getID() + SPACE + goSpecificInteractionID + SPACE + position.getX() + SPACE + position.getY();
						try {
							outstream.writeObject(msg);
							outstream.flush();
						} catch (IOException e) {
							// do nothing
						}
						**/
					}
				}
			}
		} catch (UnmodifiableGameObjectException e) {
			// do nothing
		}
	}
	
	public List<GameObject> getSelectedUnits(){
		return selectedUnits;
	}
}
