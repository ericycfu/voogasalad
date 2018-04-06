package game_player.visual_element;

import java.util.Map;

import game_player.Element;

/**
 * the interface that all top panel UI elements implement
 * 
 */
public interface TopPanel extends Element {
	
	/**
	 * allow the game player to set the resources amounts displayed in the top panel
	 * @param amount1 amount for first resource
	 * @param amount2 amount for second resource
	 */
	public void setResourcesAmount(int amount1, int amount2);
	
	/**
	 * allow the game player to set the time displayed in the top panel
	 * @param time current time
	 */
	public void setTime(double time);
	
	/**
	 * allow the game player to set the scores displayed in the top panel
	 * @param scores current scores for each player
	 */
	public void setScores(Map<String, Integer> scores);
	
}
