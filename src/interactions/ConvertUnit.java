package interactions;

import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.PropertyNotFoundException;

/**
 * 
 * @author Rayan
 * Implementation of custom function that allows you to convert units to your own team
 * 
 */



public class ConvertUnit implements CustomFunction {

	public final String NAME = "ConvertUnit";
	
	private CustomComponentParameterFormat format;
	
	@Override
	public void Execute(GameObject current, GameObject other, GameObjectManager manager) throws PropertyNotFoundException 
	{	
		other.setOwner(current.getOwner());
		other.getRenderer().flashUnit();

	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		// TODO Auto-generated method stub
		return format;
	}

	@Override
	public void setParameterFormatFields() 
	{
		format.addHelpText("This function converts a unit to your team.");

	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
	}

	@Override
	public boolean isRepetitive() {
		// TODO Auto-generated method stub
		return false;
	}

}
