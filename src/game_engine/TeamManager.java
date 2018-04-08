package game_engine;

import java.util.Map;
import java.util.TreeMap;

public class TeamManager implements ElementManager<Team>{

	private Map<Integer, Team> teamMap;
	
	public TeamManager()
	{
		teamMap = new TreeMap<>();
	}
	
	@Override
	public int addElementToManager(Team element) {
		int id = 1;
		if(teamMap.isEmpty())
		{
			teamMap.put(id, element);
		}
		else
		{
			id = teamMap.size() + 1;
			teamMap.put(id, element);
		}
		return id;
	}

	@Override
	public void removeElement(Team element) {
		
		teamMap.remove(element.getID());
	}
	

}
