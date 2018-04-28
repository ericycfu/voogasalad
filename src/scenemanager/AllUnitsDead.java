package scenemanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.EndStateWrapper;
import game_engine.Team;
import game_object.GameObject;
import interactions.CustomComponentParameterFormat;

/**
 * 
 * @author Rayan
 * Implementation of loss condition
 */

public class AllUnitsDead implements EndCondition {

	public final String NAME = "AllUnitsDead";
	
	private CustomComponentParameterFormat format;
	
	public AllUnitsDead()
	{}
	
	@Override
	public EndStateWrapper check(List<Team> teams, List<GameObject> gameObjects) {
		
		Map<Team, Integer> unitCount = new HashMap<>();
		for(Team team : teams)
		{
			unitCount.put(team, 0);
		}
		
		for(GameObject o : gameObjects)
		{
			Team objectTeam = o.getOwner();
			int newUnitCount = unitCount.get(objectTeam) + 1;
			unitCount.put(objectTeam, newUnitCount);
		}
		
		for(Map.Entry<Team, Integer> entry : unitCount.entrySet())
		{
			if(entry.getValue() == 0)
			{
				Team team = entry.getKey();
				return new EndStateWrapper(getVictoryMessage(team.getTeamName()), EndStateWrapper.EndState.LOSE, team);
			}
		}
		
		return new EndStateWrapper("", EndStateWrapper.EndState.CONTINUE, null);
		
	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		return format;
	}

	@Override
	public void setParameterFormatFields() {

		format.addHelpText("You lose when all your units and buildings are dead..");
	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getVictoryMessage(String teamName) {
		return String.format("%s has lost because they have no units or buildings left.", teamName);  
	}
	
}
