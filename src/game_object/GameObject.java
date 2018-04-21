package game_object;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import game_engine.EngineObject;
import game_engine.Team;
import interactions.Interaction;
import javafx.scene.image.Image;
import map.GridMap;
import map.Pathfinder;
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
public class GameObject implements InterfaceGameObject, EngineObject {
	
	public static final String EMPTY = "empty";
	
	private int id;
	private Transform transform;	
	private ObjectLogic myObjectLogic;
	@XStreamOmitField
	private transient Renderer renderer;
	private Team owner;
	
	private String name;
	private List<String> tag;
	private boolean isBuilding;
	
	private boolean isInteractionQueued;
	private GameObject interactionTarget;
	
	private boolean isDead;
	
	private double movementSpeed = 0;
	private boolean isMovementQueued;
	private Queue<Vector2> activeWaypoints;

	
	
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
		this.myObjectLogic = new ObjectLogic();
		propertiesInit();
	}
	
	/**
	 * 
	 * @param id
	 * @param transform
	 * @param logic
	 * Constructor for game data
	 */
	public GameObject(int id, Transform transform, ObjectLogic logic)
	{
		this.id = id;
		this.transform = transform;
		this.myObjectLogic = logic;
		propertiesInit();
	}
	
	/**
	 * 
	 * @param startingPosition
	 * @param tag
	 * @param name
	 * Standard constructor. Encouraged to use this
	 */
	public GameObject(int id, Vector2 startingPosition, List<String> tags, String name, Team t)
	{
		this.transform = new Transform(startingPosition);
		this.myObjectLogic = new ObjectLogic();
		this.renderer = new Renderer();
		this.id = id;
		this.name = name;
		this.tag = tag;
		propertiesInit();

	}
	
	private void propertiesInit()
	{
		isInteractionQueued = false;
		interactionTarget = null;
		isDead = false;
		isBuilding = false;
		activeWaypoints = new LinkedList<>();
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
		
		moveUpdate();
		
		if(isInteractionQueued)
		{
			 myObjectLogic.executeInteractions(this, interactionTarget);
		}
		//myObjectLogic.checkConditions(this);
		
		

	}
	
	private void moveUpdate()
	{
		if(isMovementQueued && !activeWaypoints.isEmpty())
		{
			if(!transform.MoveTowards(new Transform(activeWaypoints.peek()), movementSpeed))
			{
				activeWaypoints.remove();
				if(activeWaypoints.isEmpty()) dequeueMovement();
			}
		}
	}
	
	public void setIsBuilding(boolean val)
	{
		this.isBuilding = val;
	}
	
	public boolean isBuilding()
	{
		return isBuilding;
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
	public void queueInteraction(GameObject other, int id, List<GameObject> objList)
	{
		isInteractionQueued = true;
		interactionTarget = other;
		this.myObjectLogic.setCurrentInteraction(id, this, other, objList);
	}
	
	/**
	 * Interaction dequeued after it is completed or cancelled
	 */
	public void dequeueInteraction()
	{
		isInteractionQueued = false;
		interactionTarget = null;
	}
	
	public void queueMovement(Vector2 target, List<GameObject> objectList)
	{
		Pathfinder pathfinder = new Pathfinder(new GridMap());
		Queue<Vector2> movementPoints = pathfinder.findPath(this, target, objectList);
		if(!movementPoints.isEmpty())
		{
			isMovementQueued = true;
			
		}
	}
	
	public void dequeueMovement()
	{
		isMovementQueued = false;
	}
	
	public Transform getTransform() {
		return this.transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}
	
	public ObjectLogic accessLogic() throws UnmodifiableGameObjectException
	{
		if(myObjectLogic != null)
			return this.myObjectLogic;
		throw new UnmodifiableGameObjectException("Null object logic unit");
	} 
	
	public List<String> getTags() {
		if(tag == null)
			return new ArrayList<String>();
		else
			return tag;
	}

	public void setTags(List<String> tag) {
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
	
	@Override
	public int getID()
	{
		return id;
	}
	
	public Team getOwner() {
		return owner;
	}

	public double getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(double movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

}
