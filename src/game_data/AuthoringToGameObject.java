package game_data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring.backend.AuthoringObject;
import authoring.support.DraggableImageView;
import game_engine.ResourceManager;
import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.ObjectLogic;
import transform_library.Transform;
import transform_library.Vector2;
/**
 * for converting objects in the authoring environment to objects usable by player and engine
 * @author shichengrao
 *
 */
public final class AuthoringToGameObject {

	private AuthoringToGameObject() {

	}
	/**
	 * takes a map and transforms it to a gameobject manager.
	 * @param map
	 * @return
	 */
	public static GameObjectManager convertMap(Map<AuthoringObject, List<AuthoringObject>> map, List<Team> teamList) {
		GameObjectManager GOM = new GameObjectManager();
		for(AuthoringObject AO: map.keySet()) {
			for(AuthoringObject AO2: map.get(AO)) {
				DraggableImageView DIV =  AO2.getDragImage();
				ObjectLogic logic = new ObjectLogic(AO.getObjectLogic());
				GOM.createGameObject(new Transform(new Vector2(DIV.getX(), DIV.getY())),logic, AO.getMainComponentPropertyManager(), convert(AO, teamList));
			}
		}
		return GOM;
	}
	/**
	 * takes a list of authoring objects and makes them a list of game objects
	 * @param list
	 * @return
	 */
	public static List<GameObject> convertList(List<AuthoringObject> list){
		List<GameObject> gameObjectList = new ArrayList<>();
		for(AuthoringObject AO: list) {
			Transform trans = new Transform(new Vector2(0,0));
			ObjectLogic logic = new ObjectLogic(AO.getObjectLogic());
			gameObjectList.add(new GameObject(0, trans, logic, AO.getMainComponentPropertyManager(), null));
		}
		
		
		return gameObjectList;
	}
	/**
	 * takes an authoring object and a resourcemanager, makes a team object with a copy of the resource manager
	 * @param AO
	 * @param RM
	 * @return
	 */
	private static Team convert(AuthoringObject AO, List<Team> teamList) {
		for(Team team: teamList) {
			if(AO.getTeam() == team.getID()) {
				return team;
			}
		}
		System.out.println("Shouldn't ever get here");
		return null;
	}
	
	/**
	 * sees how many teams authoring has, and makes the correct amount
	 * @param resourceManager 
	 * @param map 
	 * @param list
	 * @return
	 */
	public static List<Team> calculateTeams(Map<AuthoringObject, List<AuthoringObject>> map, ResourceManager RM){
		List<Team> teams = new ArrayList<>();
		List<Integer> teamIds = new ArrayList<>();
		for(List<AuthoringObject> AOs: map.values()) {
			for(AuthoringObject AO: AOs) {
				System.out.println("This AO has team id of : " + AO.getTeam());
				if(!teamIds.contains(AO.getTeam())) {
					teams.add(new Team(AO.getTeam(), (new ResourceManager()).copyResourceManager(RM)));
					teamIds.add(AO.getTeam());
				}
			}
				
		}
		return teams;
	}
}
