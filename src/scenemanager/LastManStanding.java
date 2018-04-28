package scenemanager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game_engine.EndStateWrapper;
import game_engine.Team;
import game_object.GameObject;
import interactions.CustomComponentParameterFormat;

public class LastManStanding implements EndCondition {
	
	public final String NAME = "LastManStanding";
	private CustomComponentParameterFormat format;

	@Override
	public EndStateWrapper check(List<Team> teams, List<GameObject> gameObjects) {
		
		Set<Team> teamsFound = new HashSet<>();
		for(GameObject o : gameObjects)
		{
			teamsFound.add(o.getOwner());
		}
		if(teamsFound.size() == 1) 
		{
			Team team = teamsFound.iterator().next();
			return new EndStateWrapper(getVictoryMessage(team.getTeamName()), EndStateWrapper.EndState.WIN, team);
		}
		return new EndStateWrapper("", EndStateWrapper.EndState.CONTINUE, null);

		
	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		// TODO Auto-generated method stub
		return format;
	}

	@Override
	public void setParameterFormatFields() {
		
		format.addHelpText("Victory is achieved through this condition when you are the last one standing");

	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
	}

	@Override
	public String getVictoryMessage(String teamName) {
		// TODO Auto-generated method stub
		return String.format("%s has won because they are the last one remaining.", teamName);  
	}

}
