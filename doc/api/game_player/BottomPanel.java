package game_player;

/**
 * interface that all bottom panel UI elements implement
 * 
 */
public interface BottomPanel extends Element{
	
	/**
	 * Give the GamePlayer the ability to set the information displayed
	 * @param go the game object selected
	 */
	public void setInfo(GameObject go);
	
}
