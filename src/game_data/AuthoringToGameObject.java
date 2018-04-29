package game_data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring.backend.AuthoringObject;
import authoring.backend.DraggableImageView;
import game_engine.ResourceManager;
import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
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
	public static GameObjectManager convertMap(Map<AuthoringObject, List<DraggableImageView>> map, ResourceManager RM) {
		GameObjectManager GOM = new GameObjectManager();
		for(AuthoringObject AO: map.keySet()) {
			for(DraggableImageView DIV: map.get(AO)) {
				GOM.createGameObject(new Transform(new Vector2(DIV.getX(), DIV.getY())),AO.getObjectLogic(), AO.getMainComponentPropertyManager(), convert(AO, RM));
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
			gameObjectList.add(new GameObject(0,new Transform(new Vector2(0,0)), AO.getObjectLogic(), AO.getMainComponentPropertyManager(), null));
		}
		
		
		return gameObjectList;
	}
	/**
	 * takes an authoring object and a resourcemanager, makes a team object with a copy of the resource manager
	 * @param AO
	 * @param RM
	 * @return
	 */
	private static Team convert(AuthoringObject AO, ResourceManager RM) {

//		return new Team(AO.getTeam(),(new ResourceManager()).copyResourceManager(RM));

		return null;
	}
}
