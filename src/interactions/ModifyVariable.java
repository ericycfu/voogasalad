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

	public final String VARIABLE = "Variable";
	public final String DELTA = "Delta";
	public final String RATE = "Rate";
	
	private CustomFunctionParameterFormat format;
	
	private String variable;
	private double delta;
	
	//reconsider rate, maybe add a limit to simulate a for loop? and couple that with rate?
	private double rate;
	
	public ModifyVariable()
	{
		setParameterFormatFields();
		format = new CustomFunctionParameterFormat();
	}
	
	
	// make seperate container object for parameter data? 
	
	public void setParameters(CustomFunctionParameterFormat format)
	{
		try 
		{
			this.variable = format.getParameterValue(VARIABLE);
			this.delta = Double.parseDouble(format.getParameterValue(DELTA));
		} 
		catch (PropertyNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("Improper custom function variable assignment");
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
			System.out.println("Improper format for variable");
		}
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

	
	
	@Override
	public CustomFunctionParameterFormat getParameterFormat() {
		
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
