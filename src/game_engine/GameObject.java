package game_engine;
/**
 * 
 * @author andrew, Rayan
 * 
 * Any object that will be shown on the world screen will be of the GameObject type. 
 * 
 * Has a Transform object for operations relating to positioning world space
 *
 */
public class GameObject implements IGameObject{
	
	
	Transform transform;
	String tag;
	
	public GameObject()
	{
		this.transform = new Transform(new Vector2(0,0));
	}
	
	public GameObject(Vector2 startingPosition)
	{
		this.transform = new Transform(startingPosition);
	}
	
	public GameObject(Vector2 startingPosition, String tag)
	{
		this.transform = new Transform(startingPosition);
		this.tag = tag;
	}

	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
