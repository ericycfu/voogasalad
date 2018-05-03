package game_object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import authoring.backend.MainComponentPropertyManager;
import game_engine.EngineObject;
import game_engine.Team;
import game_engine.Timer;
import interactions.Interaction;
import pathfinding.GridMap;
import pathfinding.Pathfinder;
import transform_library.Transform;
import transform_library.Vector2;

/**
 * 
 * @author Rayan, andrew, shichengrao
 * 
 * Any object that will be shown on the world screen will be of the GameObject type. 
 * 
 * Has a Transform object for operations relating to positioning in world space
 *
 */
public class GameObject  implements InterfaceGameObject, EngineObject, Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String EMPTY = "empty";
	
	private int id;
	private Transform transform;	
	private ObjectLogic myObjectLogic;
	private Renderer renderer;
	

	private Team owner;
	
	private String name;
	private List<String> tags;
	private boolean isBuilding;
	
	private boolean isInteractionQueued;
	private GameObject interactionTarget;
	private Vector2 emptyPosTarget;
	
	private boolean isDead;
	
	
	private double movementSpeed = 0;
	private boolean isMovementQueued;
	private Queue<Vector2> activeWaypoints;
	
	private GameObjectManager manager;
	
	private boolean isUninteractive;
	
	private Timer buildTimer;
	private Timer interactionTimer;
	private boolean isBeingConstructed;
	
	private double elapsedTime;
	
	private boolean isPreviousInteractionQueued;
	
	
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
		this.renderer = new Renderer();
		propertiesInit();
	}
	/**
	 * 
	 * @param id
	 * @param transform
	 * @param logic
	 * Constructor for game object given authoring object data
	 */
	public GameObject(int id, Transform transform, ObjectLogic logic, MainComponentPropertyManager manager, Team team)
	{
		this.id = id;
		this.transform = transform;
		this.myObjectLogic = logic;
		this.movementSpeed = manager.getMovementSpeed();
		this.isBuilding = manager.isBuilding();
		System.out.println("manager.isBuilding" + manager.isBuilding());
		this.renderer = new Renderer(manager.getImagePath());
		this.name = manager.getName();
		this.tags = manager.getTags();
		this.owner = team;
		propertiesInit();
		
	}
	/**
	 * 
	 * @param startingPosition
	 * @param tags
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
		this.tags = tags;
		this.owner = t;
		propertiesInit();

	}
	
	/**
	 * 
	 * @param other
	 * Constructor that deep copies an object.
	 */
	public GameObject(int id, GameObject other)
	{
		if(other == null)
		{
			System.out.println("other null");
		}
		this.id = id;
		this.name = other.name;
		this.tags = other.tags;
		this.owner = other.owner;
		this.isBuilding = other.isBuilding;
		this.movementSpeed = other.movementSpeed;
		this.propertiesInit();
		
		this.transform = new Transform(other.getTransform());
		this.myObjectLogic = new ObjectLogic(other.myObjectLogic);
		this.renderer = new Renderer(other.renderer);


	}
	
	private void propertiesInit()
	{
		isInteractionQueued = false;
		interactionTarget = null;
		isDead = false;
		isUninteractive = false;
		activeWaypoints = new LinkedList<>();
		this.elapsedTime = 0;
		this.isPreviousInteractionQueued = false;
	}
	
	/**
	 * This function will be called at each step. Any type of object handling must be made here
	 */
	public void Update()
	{
		if(isDead())
		{
			return;
		}
		
		if(isBeingConstructed)
		{
			if(buildTimer.timeLimit(elapsedTime, this.myObjectLogic.accessAttributes().getBuildTime()))
			{
				this.dequeueBuilding();
			}
		}
		
		if(this.isUninteractive) 
			return;
		
		moveUpdate();
		
		if(this.isPreviousInteractionQueued)
		{	
			if(interactionTimer.timeLimit(elapsedTime, this.myObjectLogic.getCurrentInteraction().getRate()))
			{
				executeValidatedInteraction();			
			}
		}
		else
		{
			if(isInteractionQueued)
			{
				executeValidatedInteraction();			
			}
			
		}
	
		myObjectLogic.checkConditions(this);
		renderer.updateRenderer();

	}
	
	private void executeValidatedInteraction()
	{
		if(interactionTarget.isDead())
		{
			isInteractionQueued = false;
			interactionTarget = null;
			isPreviousInteractionQueued = false;
		}
		if(interactionTarget == null  && emptyPosTarget == null) 
			return;
		 myObjectLogic.executeInteractions(this, interactionTarget, emptyPosTarget, manager);
	}
	
	private void moveUpdate()
	{
		if(isMovementQueued && !activeWaypoints.isEmpty())
		{
			if(!transform.MoveTowards(new Transform(activeWaypoints.peek()), movementSpeed))
			{
				activeWaypoints.remove();
				if(activeWaypoints.isEmpty()) 
					dequeueMovement();
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
	public void queueInteraction(GameObject other, int id, GameObjectManager manager, GridMap gridMap, Vector2 emptyPos)
	{
		isInteractionQueued = true;
		interactionTarget = other;
		emptyPosTarget = emptyPos;
		this.myObjectLogic.setCurrentInteraction(id, this, other, manager, gridMap, emptyPos);
		this.manager = manager;
		this.isPreviousInteractionQueued = false;
	}
	
	/**
	 * Interaction dequeued after it is completed or cancelled
	 */
	public void dequeueInteraction(int interId)
	{
		Interaction inter = this.myObjectLogic.accessInteractions().getInteraction(interId);
		if(!inter.isRepetitive())
		{
			isInteractionQueued = false;
			interactionTarget = null;
		}
		else
		{
			this.isPreviousInteractionQueued = true;
			interactionTimer = new Timer();
			triggerTimer(this.interactionTimer);
		}
		
	}
	
	public void queueMovement(Vector2 target, GameObjectManager manager, GridMap gridmap)
	{
		if(this.isUninteractive || this.isBuilding)
		{
			return;
		}
		this.isPreviousInteractionQueued = false;
		this.manager = manager;
		Pathfinder pathfinder = new Pathfinder(gridmap);
		activeWaypoints = pathfinder.findPath(this, target, manager);
		if(!activeWaypoints.isEmpty())
		{
			isMovementQueued = true;
			
		}
	}
	
	public void queueBuilding()
	{
		setIsUninteractive(true);
		this.isBeingConstructed = true;
		this.buildTimer = new Timer();
		triggerTimer(this.buildTimer);
		this.renderer.reduceOpacity(Renderer.TEMP_OPACITY);
	}
	
	private void triggerTimer(Timer timer)
	{
		timer.setTimerOn(true);
		timer.setInitialTime(elapsedTime);
	}
	
	public void dequeueBuilding()
	{
		System.out.println("deq eueu called");
		this.renderer.setDefaultOpacity();
		this.setIsUninteractive(false);
		isBeingConstructed = false;
	}
	
	public void setIsUninteractive(boolean val)
	{
		this.isUninteractive = val;
	}
	
	public boolean isUninteractive()
	{
		return this.isUninteractive;
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
		if(tags == null)
			return new ArrayList<String>();
		else
			return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
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
	
	public void setOwner(Team team)
	{
		this.owner = team;
	}

	public double getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(double movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public void setElapsedTime(double time)
	{
		this.elapsedTime += time;
		this.renderer.setElapsedTime(time);
	}
	
	public void setupImages() {
		renderer.setupImage();
		myObjectLogic.setupImage();
	}
}
