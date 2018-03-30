package game_engine;
/**
 * Codes for an instance of a game that is currently being run
 * @author andrew
 *
 */
public interface GameInstance {
	/**
	 * Sets up the GameInstance based on the information in the file
	 * @param filepath
	 */
	public void setUp(String filepath);
	/**
	 * Saves the information in the GameInstance to the specified file
	 * @param filepath
	 */
	public void save(String filepath);
	/**
	 * Read commands from the file that is updated by the GamePlayer
	 * @param filepath
	 */
	public void readCommands(String filepath);
	/**
	 * Writes the current state of the Map to the file that the GamePlayer can then grab information from
	 * @param filepath
	 */
	public void writeState(String filepath);
}
