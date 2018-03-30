package game_engine;
/**
 * Represents the Game itself, which includes available maps, available units, and available 
 * @author andrew
 *
 */
public interface Game {
	/**
	 * Sets up the Game from the specified game file
	 */
	public void setUp(String filepath);
	/**
	 * Creates and runs a new GameInstance and GamePlayer for the map
	 * @param index
	 */
	public void runMap(int index);
}
