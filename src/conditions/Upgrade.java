package conditions;

import game_object.GameObject;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;
import interactions.CustomComponentParameterFormat;
import interactions.ParameterParser;

/**
 * @author Rayan
 * This CustomCondition allows users to upgrade their stats.
 */

public class Upgrade implements CustomCondition {

	public final String VARIABLE = "Attribute";
	public final String PARAMETER = "New Maximum Value";
	private CustomComponentParameterFormat format;
	
	private String attribute;
	private String delta;
	
	public Upgrade()
	{
		format = new CustomComponentParameterFormat();
		setParameterFormatFields();
	}
	
	@Override
	public void Execute(GameObject current) {
			
		ParameterParser p = new ParameterParser();
		
		try 
		{
			this.attribute = format.getParameterValue(VARIABLE);
			this.delta = format.getParameterValue(PARAMETER);
			double deltaVal = p.assignValidatedValue(PARAMETER, current);
			double prevVal = current.accessLogic().accessAttributes().getMaxAttributeVal(attribute);
			current.accessLogic().accessAttributes().setMaximumAttributeValue(attribute, prevVal + deltaVal);
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
			this.delta = format.getParameterValue(PARAMETER);
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
