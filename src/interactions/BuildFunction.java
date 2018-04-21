package interactions;

import game_object.GameObject;
import game_object.PropertyNotFoundException;

public class BuildFunction implements CustomFunction 
{
	
	private CustomComponentParameterFormat format;
	
	@Override
	public void Execute(GameObject current, GameObject other) 
	{
		
	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		return format;
	}

	@Override
	public void setParameterFormatFields() {
		format.addHelpText("This function allows you to build a unit." );
		format.addStringField("buildTime");	
		
	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {
		format = toFormat;
	}

}
