package game_object;

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
	
	public ObjectLogic()
	{
		this.attributes = new ObjectAttributes();
		interactions = new InteractionManager();
	}
	
	public ObjectAttributes accessAttributes()
	{
		return attributes;
	}
	
	public InteractionManager accessInteractions() {
		return interactions;
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
	public boolean getFulFillsLossCondition() {
		return fulfillsLossCondition;
	}
	
}
