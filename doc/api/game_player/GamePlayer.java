package game_player;

/**
 * GamePlayer is the main interface implemented by GamePlayerClass
 *
 */
public interface GamePlayer {
	
	/**
	 * constructor of GamePlayer
	 * @param reader reader passed in by GameData
	 */
	public GamePlayer(Reader reader);
	
	/**
	 * called by Driver step function to update the GamePlayer
	 */
	public void update();
}
