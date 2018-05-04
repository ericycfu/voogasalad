package interactions;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import game_engine.InvalidResourceValueException;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;

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
		if(other == null) return;
		try 
		{

			Map<String, Double> costs = other.accessLogic().accessAttributes().getCosts();
			for(Map.Entry<String, Double> entry : costs.entrySet())
			{
				String resource = entry.getKey();
				double playerStockpile = current.getOwner().getResourceManager().getResource(resource);
				if(playerStockpile >= entry.getValue()) 
				{
					double delta = playerStockpile - entry.getValue();
					current.getOwner().getResourceManager().updateResource(resource, delta);
				}
	
			}
		} 
		catch (UnmodifiableGameObjectException | InvalidResourceValueException e) {
			e.printStackTrace();
		}
		
		int newObjId = manager.copyGameObject(other);
		manager.getGameObject(newObjId).setOwner(current.getOwner());
		manager.getGameObject(newObjId).queueBuilding();
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

	@Override
	public boolean isRepetitive() {
		// TODO Auto-generated method stub
		return false;
	}

}
