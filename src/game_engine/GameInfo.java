package game_engine;

import java.util.ArrayList;
import java.util.List;

import game_object.GameObject;
import game_object.ObjectLogic;
import game_object.UnmodifiableGameObjectException;

/**
 * The GameInfo is responsible for storing information about the Game
 * @author andrew
 *
 */
public class GameInfo {
	public List<GameObject> ListOfGameObjs;
	/**
	 * Retrieve the possible units associated with a particular player
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
			if(hasTags)
				toReturn.add(go);
		}
		return toReturn;
	}
	/**
	 * Retrieve the possible interactions for a particular GameObject
	 * @param g
	 * @throws UnmodifiableGameObjectException 
	 */
	public ObjectLogic getPossibleInteractions(GameObject g) throws UnmodifiableGameObjectException {
		return g.accessLogic();
	}
}
