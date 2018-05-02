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
		System.out.println("death gets executed!");
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

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {
		// TODO Auto-generated method stub
		
	}

}
