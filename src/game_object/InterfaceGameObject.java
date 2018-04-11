package game_object;

import transform_library.Transform;

/**
 * 
 * @author Rayan
 * Ensures that no gameobject is created without a transform component. Allows us to play with the 
 * movement of a gameobject regardless of what type it is.
 */
public interface InterfaceGameObject {

	public Transform getTransform();
	public void setTransform(Transform transform);
	
}
