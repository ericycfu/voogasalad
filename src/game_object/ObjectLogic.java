package game_object;

import java.util.ArrayList;
import java.util.List;

import interactions.Interaction;
import interactions.InteractionManager;
import transform_library.Vector2;


/**
 * 
 * @author Rayan
 * GameUnit contains all the game logic for the object that the user will be allowed to interact with like
 * attributes and interactions
 */

public class ObjectLogic  
{
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
	
	
	/**
	 * 
	 * @param current
	 * @param interactionTarget
	 * 
	 * Executes interaction if within the given range
	 */
	public void executeInteractions(GameObject current, GameObject interactionTarget)
	{
		for(Interaction interaction : interactions.getElements())
		{
			if(current.getTransform().getDisplacement(interactionTarget.getTransform()) >= interaction.getRange())
			{
				interaction.executeCustomFunctions(current, interactionTarget);
			}
		}
	}
	
}
