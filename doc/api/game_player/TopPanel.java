package game_player;

public interface TopPanel extends Element {
	
	public void setResourcesAmount(int amount1, int amount2);
	
	public void setTime(double time);
	
	public void setScores(Map<String, Integer> scores);
	
}
