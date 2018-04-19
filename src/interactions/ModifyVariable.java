package interactions;

import game_object.GameObject;
import game_object.ObjectLogic;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;

/**
 * 
 * @author Rayan
 * Standard game logic button, changes the value of variables in game objects.
 *
 */

public class ModifyVariable implements CustomFunction {

	public final String VARIABLE = "Variable";
	public final String DELTA = "Delta";
	public final String RATE = "Rate";
	
	private CustomComponentParameterFormat format;
	
	private String variable;
	private double delta;
	
	//reconsider rate, maybe add a limit to simulate a for loop? and couple that with rate?
	private double rate;
	
	public ModifyVariable()
	{
		format = new CustomComponentParameterFormat();
		setParameterFormatFields();
	}
	
	
	// make seperate container object for parameter data? 
	
	public void setParameters(CustomComponentParameterFormat format)
	{
		try 
		{
			this.variable = format.getParameterValue(VARIABLE);
			this.delta = Double.parseDouble(format.getParameterValue(DELTA));
		} 
		catch (PropertyNotFoundException e) 
		{
			System.out.println("Improper custom function variable assignment");
		}
		catch (NumberFormatException e)
		{
			System.out.println("Improper format for variable");
		}
	}
	
	/**
	 * Will get variable list from object and subtract from relevant variable
	 */
	@Override
	public void Execute(GameObject current, GameObject other) {
		
		//largely placeholder implementation, will have to take care of rate
		double prevVal;
		
		try 
		{
			prevVal = other.accessLogic().accessAttributes().getAttribute(variable);
			other.accessLogic().accessAttributes().setAttributeValue(variable, prevVal + delta);
			current.dequeueInteraction();
		} 
		catch (PropertyNotFoundException | UnmodifiableGameObjectException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		return format;
	}


	@Override
	public void setParameterFormatFields() {
		
		format.addHelpText("This function allows you to change a variable in another object when the "
				+ "interaction occurs. Variable = Variable you can change. Delta = The change that must take place.");
		format.addStringField(VARIABLE);
		format.addStringField(DELTA);
		format.addStringField(RATE);		
	}
	

}
