package scenemanager;

import java.util.List;

import game_engine.InvalidResourceValueException;
import game_engine.ResourceManager;
import game_engine.Team;
import game_object.GameObject;
import game_object.PropertyNotFoundException;
import interactions.CustomComponentParameterFormat;

public class ResourceVictory implements WinCondition {

	public final String NAME = "ResourceVictory";
	public final String RESOURCE = "Resource";
	public final String THRESHOLD = "Threshold";

	
	private CustomComponentParameterFormat format;
	
	private String resource;
	private double threshold;
	
	@Override
	public boolean check(Team team, List<GameObject> gameObjects) {
		
		try
		{
			double resourceVal = team.getResourceManager().getResource(resource);
			return (resourceVal > threshold);
		} 
		catch (InvalidResourceValueException e) 
		{
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		return format;
	}

	@Override
	public void setParameterFormatFields() {
		
		format.addHelpText("Victory is achieved through this condition if any player collects more "
				+ "reources than the threshold.");
		format.addStringField(RESOURCE);
		format.addStringField(THRESHOLD);
	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {
		
		try 
		{
			this.resource = format.getParameterValue(RESOURCE);
			this.threshold = Double.parseDouble(format.getParameterValue(THRESHOLD));
		
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
		return NAME;
	}
	
	
}
