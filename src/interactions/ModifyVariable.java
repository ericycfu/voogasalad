package interactions;

import game_object.GameObject;
import game_object.ObjectLogic;
import game_object.PropertyNotFoundException;

/**
 * 
 * @author Rayan
 * Standard game logic button, changes the value of variables in game objects.
 *
 */

public class ModifyVariable implements CustomFunction {

	private String variable;
	private double delta;
	private double rate;
	
	public ModifyVariable()
	{}
	
	public void setParameters(String variable, double delta, double rate)
	{
		this.variable = variable;
		this.delta = delta;
		this.rate = rate;
	}
	
	/**
	 * Will get variable list from object and subtract from relevant variable
	 */
	@Override
	public void Execute(ObjectLogic obj) {
		
		//largely placeholder implementation, will have to take care of rate
		double prevVal;
		
		try 
		{
			prevVal = obj.accessAttributes().getAttribute(variable);
			obj.accessAttributes().SetAttributeValue(variable, prevVal + delta);
		} 
		catch (PropertyNotFoundException e) 
		{
			e.printStackTrace();
		}
		
	}

}
