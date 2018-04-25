package scenemanager;

import java.util.List;

import game_engine.EndStateWrapper;
import game_engine.Team;
import game_object.GameObject;
import interactions.CustomComponentParameterFormat;

public class AllUnitsDead implements EndCondition {

	public final String NAME = "AllUnitsDead";
	
	private CustomComponentParameterFormat format;
	
	public AllUnitsDead()
	{
		
	}
	
	@Override
	public EndStateWrapper check(Team team, List<GameObject> gameObjects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		// TODO Auto-generated method stub
		return format;
	}

	@Override
	public void setParameterFormatFields() {

		format.addHelpText("Victory is achieved through this condition when all units of one team are dead.");
	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
	}

	@Override
	public String getVictoryMessage(String teamName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
