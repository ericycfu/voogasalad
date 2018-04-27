package scenemanager;

import java.util.List;

import game_engine.EndStateWrapper;
import game_engine.Team;
import game_object.GameObject;
import interactions.CustomComponentParameterFormat;

public class AllUnitsDead implements EndCondition {

	@Override
	public EndStateWrapper check(Team team, List<GameObject> gameObjects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParameterFormatFields() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVictoryMessage(String teamName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
