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
	
	public final String NAME = "BuildFunction";
	private CustomComponentParameterFormat format;
	
	public BuildFunction()
	{
		format = new CustomComponentParameterFormat();
		setParameterFormatFields();
	}
	
	@Override
	public void Execute(GameObject current, GameObject other, GameObjectManager manager) 
	{
		if(other == null) {
			System.out.println("other is null");
			return;
		}
		System.out.print("building");
		manager.copyGameObject(other, current.getOwner());
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
		
	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {
		format = toFormat;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
	}

}
