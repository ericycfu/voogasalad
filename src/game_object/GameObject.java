package game_object;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
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
	private ObjectLogic objectLogic;
	private Renderer renderer;
	
	private String name;
	private String tag;
	
	private Image img;
	// SHOULD BE IN GAME OBJECT MANAGER
	private boolean isSelected;
	private boolean isTarget;
	/**
	 *		myDisp.setOnMouseClicked(e -> {
			if (e.isPrimaryButtonDown()) {
				
			}
			if (e.isSecondaryButtonDown()) {
				
			}
		});
	 */
	
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
		this.renderer = new Renderer(img);
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
		this.objectLogic = new ObjectLogic();
		this.renderer = new Renderer(img);
		this.name = name;
		this.tag = tag;
	}
	
	/**
	 * This function will be called at each step. Any type of object handling must be made here
	 */
	public void Update()
	{
		/**
		 *  TIMELINE: 
		 *  1. Update Transform data
		 *  2. Act upon logic data
		 *  3. Update renderer data
		 */
	}
	
	public Transform getTransform() {
		return this.transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}
	
	public ObjectLogic accessLogic() throws UnmodifiableGameObjectException
	{
		if(objectLogic != null)
			return this.objectLogic;
		throw new UnmodifiableGameObjectException("Null object logic unit");
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

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
	
	
	
}
