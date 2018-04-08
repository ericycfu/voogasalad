package game_object;

import java.util.HashMap;
import java.util.Map;

import game_engine.EngineObject;
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
public class GameObject implements InterfaceGameObject, EngineObject<GameObjectManager>{
	
	public static final String EMPTY = "empty";
	
	private int id;
	private Transform transform;	
	private ObjectLogic objectLogic;
	private Renderer renderer;
	
	private String name;
	private String tag;
	
	private boolean isInteractionQueued;
	private GameObject interactionTarget;
	
	private boolean isDead;
	
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
		this.renderer = new Renderer();
		isDead = false;
	}
	
	/**
	 * 
	 * @param startingPosition
	 * @param tag
	 * @param name
	 * Standard constructor. Encouraged to use this
	 */
	public GameObject(Vector2 startingPosition, String tag, String name, GameObjectManager manager)
	{
		this.transform = new Transform(startingPosition);
		this.objectLogic = new ObjectLogic();
		this.renderer = new Renderer();
		this.name = name;
		this.tag = tag;
		addToManager(manager);
		isInteractionQueued = false;
		interactionTarget = null;
		isDead = false;
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
		if(isInteractionQueued)
		{
			 objectLogic.executeInteractions(this, interactionTarget);
		}
	}
	
	
	public void setIsDead(boolean isDead)
	{
		this.isDead = isDead;
	}	
	
	public boolean isDead()
	{
		return isDead;
	}
	
	/**
	 * 
	 * @param other
	 * gives the signal to the gameobject that an interaction is queued
	 * Will be called by the game player when an already selected unit chooses to interact with another unit e.g. to attack
	 */
	public void queueInteraction(GameObject other)
	{
		isInteractionQueued = true;
		interactionTarget = other;
	}
	
	/**
	 * Interaction dequeued after it is completed or cancelled
	 */
	public void dequeueInteraction()
	{
		isInteractionQueued = false;
		interactionTarget = null;
	}
	
	/**
	 * 
	 * @param manager
	 * Assigns an id to the game object based on the game objects inside the game. Also assigns it to the object manager
	 * which will then allow the game player to access functions on that game object
	 */
	@Override
	public void addToManager(GameObjectManager manager)
	{
		setID(manager.addElementToManager(this));
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
	
	private void setID(int id)
	{
		this.id = id;
	}
	
	@Override
	public int getID()
	{
		return id;
	}

}
