package conditions;

import game_object.GameObject;
import game_object.Renderer;
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
		current.getRenderer().reduceOpacity(Renderer.INVISIBLE_OPACITY);

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
