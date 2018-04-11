package conditions;

import game_object.GameObject;
import interactions.CustomComponentParameterFormat;

/**
 * 
 * @author Rayan
 * Custom condition that kills the unit it is assigned to. 
 */
public class Death implements CustomCondition {
	
	private CustomComponentParameterFormat format;

	public Death()
	{
		format = new CustomComponentParameterFormat();
		setParameterFormatFields();
	}
	
	@Override
	public void Execute(GameObject current) {
		
		current.setIsDead(true);

	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		return format;
	}

	@Override
	public void setParameterFormatFields() {
		
		format.addHelpText("This function will kill the unit it is assigned to");
	}

}
