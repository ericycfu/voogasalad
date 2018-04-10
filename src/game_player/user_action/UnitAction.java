package game_player.user_action;

import java.util.List;

import game_object.GameObject;
import transform_library.Vector2;

/**
 * 
 * @author Siyuan
 *
 */
public class UnitAction {
	
	private String myActionType;
	private List<GameObject> myActors;
	private GameObject myTarget;
	private Vector2 myTargetPos;
	
	public UnitAction(String type, List<GameObject> actors, GameObject target, Vector2 targetPosition) {
		myActionType = type;
		myActors = actors;
		myTarget = target;
		myTargetPos = targetPosition;
	}

	/**
	 * @return the myActionType
	 */
	public String getActionType() {
		return myActionType;
	}

	/**
	 * @return the myActors
	 */
	public List<GameObject> getActors() {
		return myActors;
	}

	/**
	 * @return the myTarget
	 */
	public GameObject getTarget() {
		return myTarget;
	}

	/**
	 * @return the myTargetPos
	 */
	public Vector2 getTargetPos() {
		return myTargetPos;
	}
	
}