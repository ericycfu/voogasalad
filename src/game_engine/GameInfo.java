package game_engine;
/**
 * The GameInfo is responsible for storing information about the Game
 * @author andrew
 *
 */
public interface GameInfo {
	/**
	 * Retrieve the possible units associated with a particular player
	 * @param commander
	 */
	public void getUnits(String commander);
	/**
	 * Retrieve the possible interactions for a particular GameObject
	 * @param g
	 */
	public void getPossibleInteractions(GameObject g);
}
