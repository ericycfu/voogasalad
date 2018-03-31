package game_object;

import java.util.HashMap;
import java.util.Map;

import transform_library.Transform;
import transform_library.Vector2;

/**
 * 
 * @author andrew, Rayan
 * 
 * Any object that will be shown on the world screen will be of the GameObject type. 
 * 
 * Has a Transform object for operations relating to positioning in world space
 *
 */
public class GameObject implements InterfaceGameObject{
	
	public static final String EMPTY = "empty";
	
	private Transform transform;
	private ObjectAttributes attributes;
	
	private String name;
	private String tag;
	
	
	/**
	 * To be used in case object who's position doesn't matter or doesn't need to be set yet
	 */
	public GameObject()
	{
		this.transform = new Transform(new Vector2(0,0));
	}
	
	/**
	 *
	 * @param startingPosition
	 * To be used in case setting up static objects that do not interact with the environment or users, hence tag or name
	 * is not needed.
	 * 
	 */
	public GameObject(Vector2 startingPosition)
	{
		this.transform = new Transform(startingPosition);

	}
	
	/**
	 * 
	 * @param startingPosition
	 * @param tag
	 * @param name
	 * Standard constructor. Encouraged to use this
	 */
	public GameObject(Vector2 startingPosition, String tag, String name)
	{
		this.transform = new Transform(startingPosition);
		this.name = name;
		this.tag = tag;
		this.attributes = new ObjectAttributes();
	}
	
	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}

	public String getTag() {
		if(tag == null)
			return EMPTY;
		else
			return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getName() {
		if(name == null)
			return EMPTY;
		else
			return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
