package game_engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import game_object.GameObject;

/**
 * The GameInfo is responsible for storing information about the Game
 * @author andrew
 *
 */
public class GameInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<GameObject> ListOfGameObjs;
	public GameInfo() {
		ListOfGameObjs = new ArrayList<GameObject>();
	}
	public void addReferenceGameObject(GameObject go) {
		ListOfGameObjs.add(go);
	}
	/**
	 * Retrieve the possible units with the given tags
	 * @param commander
	 */
	public List<GameObject> getUnits(List<String> tag) {
		List<GameObject> toReturn = new ArrayList<GameObject>();
		for(GameObject go: ListOfGameObjs) {
			boolean hasTags = true;
			List<String> objectTags = go.getTags();
			for(String s: tag)
				if(!objectTags.contains(s)) {
					hasTags = false;
					break;
				}
			if(hasTags || tag.contains(go.getName()))
				toReturn.add(go);
		}
		return toReturn;
	}
	public GameObject get(String objectName) {
		for(GameObject go: ListOfGameObjs) {
			if(go.getName().equals(objectName))
				return go;
		}
		throw new IllegalArgumentException("Unit does not exist");
	}
}
