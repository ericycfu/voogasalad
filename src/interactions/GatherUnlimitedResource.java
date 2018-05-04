package interactions;

import game_engine.InvalidResourceValueException;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;

public class GatherUnlimitedResource implements CustomFunction{

	public final String NAME = "GatherUnlimitedResource";
	public final String RESOURCE = "Resource";
	public final String GATHER_RATE = "Gather Rate";
	
	
	private CustomComponentParameterFormat format;
	
	private String resource;
	private String gatherRate;
	
	public GatherUnlimitedResource()
	{
		format = new CustomComponentParameterFormat();
		setParameterFormatFields();
	}
	
	@Override
	public void Execute(GameObject current, GameObject other, GameObjectManager manager) {
		
		if(other == null) return;
		try 
		{
			this.resource = format.getParameterValue(RESOURCE);
			this.gatherRate = format.getParameterValue(GATHER_RATE);
			double deltaVal;
			ParameterParser p = new ParameterParser();
			deltaVal = p.assignValidatedValue(gatherRate, current);
			
			double prevVal = current.getOwner().getResourceManager().getResource(resource);
			current.getOwner().getResourceManager().updateResource(resource, prevVal + deltaVal);
			other.getRenderer().flashUnit();

		} 
		catch (PropertyNotFoundException | UnmodifiableGameObjectException | InvalidResourceValueException e) 
		{
		}
	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		return format;
	}

	@Override
	public void setParameterFormatFields() {
		
		format.addHelpText("This function modifies the resource stockpile upon interaction witb another unit");
		format.addStringField(RESOURCE);
		format.addStringField(GATHER_RATE);
		
	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {
		
		try 
		{
			this.resource = format.getParameterValue(RESOURCE);
			this.gatherRate = format.getParameterValue(GATHER_RATE);
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
	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
	}

	@Override
	public boolean isRepetitive() {
		// TODO Auto-generated method stub
		return true;
	}

}
