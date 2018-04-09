package game_engine;

public class Team implements EngineObject<TeamManager>{

	private int id;
	private String teamName;
	
	public Team(String teamName, TeamManager manager)
	{
		this.teamName = teamName;
		addToManager(manager);
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public int getID()
	{
		return id;
	}
	
	private void setID(int id)
	{
		this.id = id;
	}

	@Override
	public void addToManager(TeamManager manager) {
		
		setID(manager.addElementToManager(this));
	}
	
}
