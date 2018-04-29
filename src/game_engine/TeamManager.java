package game_engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Rayan
 * Team manager that creates teams and allows the game designer to set objects to specific teams
 */

public class TeamManager extends ElementManager {
		
	public TeamManager()
	{}

	public int createTeam(String teamName, ResourceManager resourceManager)
	{
		int newID = calculateID();
		Team team = new Team(newID, resourceManager);
		this.addElement(team);
		return newID;
	}
	
	public List<Team> getElements()
	{
		List<Team> teams = new ArrayList<>();
		
		for(EngineObject eObj : getElementsRaw())
		{
			Team gObj = (Team) eObj;
			teams.add(gObj);
		}
		return teams;
	}
	
	public Team getTeam(int id)
	{
		return (Team)(this.get(id));
	}
	public int getSize() {
		return getElementsRaw().size();
	}
}
