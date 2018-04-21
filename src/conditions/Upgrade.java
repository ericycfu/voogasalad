package conditions;

import game_object.GameObject;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;
import interactions.CustomComponentFunction;
import interactions.CustomComponentParameterFormat;

/**
 * @author Rayan
 * This CustomCondition allows users to upgrade their stats.
 */

public class Upgrade implements CustomCondition, CustomComponentFunction {

	public final String VARIABLE = "Attribute";
	public final String DELTA = "Delta";
	private CustomComponentParameterFormat format;
	
	private String attribute;
	private double delta;
	
	@Override
	public void Execute(GameObject current) {
		
		double prevVal;
		
		try 
		{
			prevVal = current.accessLogic().accessAttributes().getAttribute(attribute);
			current.accessLogic().accessAttributes().setAttributeValue(attribute, prevVal + delta);
		} 
		catch (PropertyNotFoundException | UnmodifiableGameObjectException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		// TODO Auto-generated method stub
		return format;
	}
	
	public void setParameters(CustomComponentParameterFormat format)
	{
		try 
		{
			this.attribute = format.getParameterValue(VARIABLE);
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

	@Override
	public void setParameterFormatFields() {
		format.addHelpText("This custom condition modifies the unit's base attribute.");
		format.addStringField("VARIABLE");
		format.addStringField("DELTA");
		
	}	


}
