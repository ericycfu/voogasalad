package game_engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import game_object.GameObject;

import java.util.TreeMap;
import java.util.AbstractMap.SimpleEntry;

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

	@Override
	public List<Team> getElements() {
		
		List<Team> teamObjectList = new ArrayList<>();
		
		for(Map.Entry<Integer, Team> var : teamMap.entrySet())
		{
			teamObjectList.add(var.getValue());
		}
		
		return Collections.unmodifiableList(teamObjectList);
	}
	public Team get(int team_id) {
		return teamMap.get(team_id);
	}



}
