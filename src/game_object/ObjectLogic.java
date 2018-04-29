package game_object;


import java.io.Serializable;

import conditions.Condition;
import conditions.ConditionManager;
import interactions.Interaction;
import interactions.InteractionManager;
import pathfinding.GridMap;
import transform_library.Vector2;


/**
 * 
 * @author Rayan
 * GameUnit contains all the game logic for the object that the user will be allowed to interact with like
 * attributes and interactions
 */

public class ObjectLogic implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean fulfillsLossCondition;
	private Interaction currentInteraction;
	
	ObjectAttributes attributes;
	InteractionManager interactions;
	ConditionManager conditions;
		
	public ObjectLogic()
	{
		this.attributes = new ObjectAttributes();
		interactions = new InteractionManager();
		conditions = new ConditionManager();
	}
	
	public ObjectAttributes accessAttributes()
	{
		return attributes;
	}
	
	public InteractionManager accessInteractions() {
		return interactions;
	}
	
	public ConditionManager accessConditions()
	{
		return conditions;
	}
	
	/**
	 * 
	 * @param current
	 * @param interactionTarget
	 * 
	 * Executes interaction if within the given range
	 * 
	 * for now it executes all interactions, later it must be specific interactions
	 */
	public void executeInteractions(GameObject current, GameObject interactionTarget, Vector2 emptyPos, GameObjectManager manager)
	{
		if(current.isUninteractive() || (interactionTarget != null && interactionTarget.isUninteractive())) return;
		if(interactionTarget == null && emptyPos != null)
		{
			if(current.getTransform().getPosition().matches(emptyPos))
			{
				current.dequeueMovement();
				currentInteraction.executeCustomFunctions(current, interactionTarget, manager);
			}	
			return;
		}
		if(inRange(current, interactionTarget, currentInteraction))
		{
			current.dequeueMovement();
			currentInteraction.executeCustomFunctions(current, interactionTarget, manager);
			current.dequeueInteraction();
		}
		
	}
	
	/**
	 * 
	 * @param current
	 * @param interactionTarget
	 * @param inter
	 * @return
	 * 
	 * Checks if two objects are in range to do an interaction
	 */
	public boolean inRange(GameObject current, GameObject interactionTarget, Interaction inter)
	{
		double dist = current.getTransform().getDisplacement(interactionTarget.getTransform()); 
		return (dist < inter.getRange());
	}

	/**
	 * 
	 * @param id
	 * @param current
	 * @param other
	 * @param manager
	 * @param gridMap
	 * @param emptyPos
	 * The interaction that needs to be executed is set for the player.
	 */
	public void setCurrentInteraction(int id, GameObject current, GameObject other, GameObjectManager manager, GridMap gridMap, Vector2 emptyPos) {
		
		currentInteraction = interactions.getInteraction(id);
		if(other == null && emptyPos != null)
		{
			current.queueMovement(emptyPos, manager, gridMap);
			return;
		}
		if(!inRange(current, other, currentInteraction))
		{
			current.queueMovement(other.getTransform().getPosition(), manager, gridMap);
		}

	}
	
	public void checkConditions(GameObject current)
	{
		for(Condition condition : conditions.getElements())
		{
			condition.execute();
		}
	}
	
	public boolean getFulFillsLossCondition() {
		return fulfillsLossCondition;
	}
	public void setupImage() {
		interactions.setupImage();
	}
}