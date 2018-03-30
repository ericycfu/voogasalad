package game_engine;
/**
 * Represents an object that exists in the game
 * @author andrew, Rayan
 *
 */
public class GameObject {
	
	Transform transform;
	String tag;
	
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
