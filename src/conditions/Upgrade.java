package conditions;

import game_object.GameObject;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;
import interactions.CustomComponentParameterFormat;
import interactions.ParameterParser;

/**
 * @author Rayan
 * This CustomCondition allows users to upgrade their unit's stats.
 */

public class Upgrade implements CustomCondition {

	public static final String VARIABLE = "Attribute";
	public static final String PARAMETER = "New Maximum Value";
	public static final String UPGRADE_VARIABLE = "Upgrade Variable";
	public static final String UPGRADE_VARIABLE_DELTA = "Upgrade Variable Delta";
	
	
	private CustomComponentParameterFormat format;
	
	private String attribute;
	private String upgradeVar;
	
	public Upgrade()
	{
		format = new CustomComponentParameterFormat();
		setParameterFormatFields();
	}
	
	@Override
	public void Execute(GameObject current) {
					
		try 
		{
			this.attribute = format.getParameterValue(VARIABLE);
			ParameterParser p = new ParameterParser();
			double deltaVal = p.assignValidatedValue(PARAMETER, current);
			
			this.upgradeVar = format.getParameterValue(UPGRADE_VARIABLE);
			double upgradeDelta = p.assignValidatedValue(UPGRADE_VARIABLE_DELTA, current);
			
			double prevVal = current.accessLogic().accessAttributes().getMaxAttributeVal(attribute);
			current.accessLogic().accessAttributes().setMaximumAttributeValue(attribute, prevVal + deltaVal);
			
			double prevDelta = current.accessLogic().accessAttributes().getMaxAttributeVal(upgradeVar);
			current.accessLogic().accessAttributes().setAttributeValue(upgradeVar, prevDelta + upgradeDelta);
			current.accessLogic().accessAttributes().setMaximumAttributeValue(upgradeVar, prevDelta + upgradeDelta);
			
			
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

	@Override
	public void setParameterFormatFields() {
		format.addHelpText("This custom condition modifies the unit's base attribute. Upgrade Variable is the"
				+ " variable that is checked for upgrade. Upgrade Variable Delta increases or decreases that upgrade threshold");
		format.addStringField(VARIABLE);
		format.addStringField(PARAMETER);
		format.addStringField(UPGRADE_VARIABLE);
		format.addStringField(UPGRADE_VARIABLE_DELTA);
		
	}	


}
