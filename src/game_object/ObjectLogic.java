package game_object;


import java.util.ArrayList;
import java.util.List;

import conditions.Condition;
import conditions.ConditionManager;
import game_engine.EngineObject;
import interactions.Interaction;
import interactions.InteractionManager;
import pathfinding.GridMap;


/**
 * 
 * @author Rayan
 * GameUnit contains all the game logic for the object that the user will be allowed to interact with like
 * attributes and interactions
 */

public class ObjectLogic  
{
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
	public void executeInteractions(GameObject current, GameObject interactionTarget, GameObjectManager manager)
	{
		if(interactionTarget.isUninteractive() || current.isUninteractive()) return;
		if(inRange(current, interactionTarget, currentInteraction))
		{
			current.dequeueMovement();
			currentInteraction.executeCustomFunctions(current, interactionTarget, manager);
			current.dequeueInteraction();
		}
		
	}
	
	public boolean inRange(GameObject current, GameObject interactionTarget, Interaction inter)
	{
		return(current.getTransform().getDisplacement(interactionTarget.getTransform()) >= inter.getRange());
	}

	public void setCurrentInteraction(int id, GameObject current, GameObject other, GameObjectManager manager, GridMap gridMap) {
		
		currentInteraction = interactions.getInteraction(id);
		if(!inRange(current, other, currentInteraction))
		{
			current.queueMovement(current.getTransform().getPosition(), manager, gridMap);
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