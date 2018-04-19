package game_engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import conditions.Condition;
import game_object.GameObject;

import java.util.TreeMap;
import java.util.AbstractMap.SimpleEntry;

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
		Team team = new Team(newID, teamName, resourceManager);
		this.addElement(team);
		return newID;
	}
}
