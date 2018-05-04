package scenemanager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game_engine.EndStateWrapper;
import game_engine.InvalidResourceValueException;
import game_engine.Team;
import game_object.GameObject;
import game_object.PropertyNotFoundException;
import interactions.CustomComponentParameterFormat;

/**
 * 
 * @author Rayan
 * This class implements ResourceVictory, which checks if the game if won when the resource stockpile passes
 * a certain level.
 */

public class ResourceVictory implements EndCondition {

	public final String NAME = "ResourceVictory";
	public final String RESOURCE = "Resource";
	public final String THRESHOLD = "Threshold";
	
	private CustomComponentParameterFormat format;
	
	private String resource;
	private double threshold;
	
	public ResourceVictory() {
		format = new CustomComponentParameterFormat();
		setParameterFormatFields();
	}
	
	@Override
	public EndStateWrapper check(List<Team> teams, List<GameObject> gameObjects) {
		
		Set<Integer> teamIDSet = new HashSet<>();
		try
		{
			this.resource = format.getParameterValue(RESOURCE);
			this.threshold = Double.parseDouble(format.getParameterValue(THRESHOLD));
			for(GameObject o : gameObjects)
			{
				Team team = o.getOwner();
				if(teamIDSet.contains(team.getID())) continue;
				teamIDSet.add(team.getID());
				double resourceVal = team.getResourceManager().getResource(resource);
				if(resourceVal > threshold)
					return new EndStateWrapper(getVictoryMessage(team.getTeamName()), 
							EndStateWrapper.EndState.WIN, team);
			}
			return new EndStateWrapper("", EndStateWrapper.EndState.CONTINUE, null);
		} 
		catch (InvalidResourceValueException | PropertyNotFoundException e) 
		{
			return new EndStateWrapper("", EndStateWrapper.EndState.CONTINUE, null);
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


	@Override
	public String getVictoryMessage(String teamName) {
		// TODO Auto-generated method stub
		return String.format("%s has achieved resource victory by collecting %d %s", teamName, threshold, resource);  
	}
	
}
