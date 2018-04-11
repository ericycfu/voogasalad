package game_object;


import java.util.ArrayList;
import java.util.List;

import conditions.Condition;
import conditions.ConditionManager;
import interactions.Interaction;
import interactions.InteractionManager;


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
	 */
	public void executeInteractions(GameObject current, GameObject interactionTarget)
	{
		if(currentInteraction != null && current.getTransform().getDisplacement(interactionTarget.getTransform()) >= currentInteraction.getRange())
		{
			currentInteraction.executeCustomFunctions(current, interactionTarget);
		}
	}

	public void setCurrentInteraction(Interaction i) {
		currentInteraction = i;
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
	
}