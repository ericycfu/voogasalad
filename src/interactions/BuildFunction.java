package interactions;

import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.PropertyNotFoundException;

/**
 * 
 * @author Rayan
 * Custom Function for building object.
 */

public class BuildFunction implements CustomFunction {
	
	private CustomComponentParameterFormat format;
	
	@Override
	public void Execute(GameObject current, GameObject other, GameObjectManager manager) 
	{
		//object may not have been added to manager
		other.queueBuilding();
	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		return format;
	}

	@Override
	public void setParameterFormatFields() {
		format.addHelpText("This function allows you to build a unit. Enter the tags for all the possible"
				+ "units that this object can create." );
		format.addStringField("buildTime");	
		
	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {
		format = toFormat;
	}

}
