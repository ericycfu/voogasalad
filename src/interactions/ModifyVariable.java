package interactions;

import java.util.regex.Pattern;

import game_object.GameObject;
import game_object.GameObjectManager;
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

	public final String NAME = "ModifyVariable";
	public final String VARIABLE = "Variable";
	public final String DELTA = "Delta";
	public final String RATE = "Rate";
	
	private CustomComponentParameterFormat format;
	
	private String variable;
	private String delta;
	
	public ModifyVariable()
	{
		format = new CustomComponentParameterFormat();
		setParameterFormatFields();
	}
	
		
	public void setParameters(CustomComponentParameterFormat format)
	{
		try 
		{
			this.variable = format.getParameterValue(VARIABLE);
			this.delta = format.getParameterValue(DELTA);
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
	public void Execute(GameObject current, GameObject other, GameObjectManager manager) {		
		
		
		if(other == null) return;
		try 
		{
			
			this.variable = format.getParameterValue(VARIABLE);
			this.delta = format.getParameterValue(DELTA);
			ParameterParser p = new ParameterParser();
			double deltaVal = p.assignValidatedValue(delta, current);
			double prevVal = other.accessLogic().accessAttributes().getAttribute(variable);
			double maxVal = other.accessLogic().accessAttributes().getMaxAttributeVal(variable);
			if(prevVal + deltaVal <= maxVal)
			{
				other.accessLogic().accessAttributes().setAttributeValue(variable, prevVal + deltaVal);
			}
			else
			{
				double finalDelta = (prevVal + deltaVal) - maxVal;
				other.accessLogic().accessAttributes().setAttributeValue(variable, prevVal + finalDelta);

			}
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
				+ "interaction occurs. Variable = Variable you can change. Delta = The change that must take place. The Delta "
				+ "can either be a number or an attribute in the player");
		format.addStringField(VARIABLE);
		format.addStringField(DELTA);
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
	}


	@Override
	public boolean isRepetitive() {
		// TODO Auto-generated method stub
		return true;
	}

}
