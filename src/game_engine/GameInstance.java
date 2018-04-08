package game_engine;
/**
 * Codes for an instance of a game that is currently being run
 * @author andrew
 *
 */
public class GameInstance {
	/**
	 * Sets up the GameInstance based on the information in the file
	 * @param filepath
	 */
	private GameInfo myGameInfo;
	private ObjectManager myObjectManager;
	private PlayerManager myPlayerManager;
	private SceneManager mySceneManager;
	
	public GameInstance(GameInfo g, String filepath) {
		setUp(filepath);
	}
	public void setUp(String filepath) {
		List<GameObject> gameObjects = myReader.read(INITIALIZATION_LOCATION, "gameObject");
		myObjectManager = new ObjectManager();
		List<LossCondition> lossConditions = myReader.read(INITIALIZATION_LOCATION,"lossCondition");
	}
	/**
	 * Saves the information in the GameInstance to the specified file
	 * @param filepath
	 */
	public void save(String filepath) {
		
	}
	/**
	 * Read commands from the file that is updated by the GamePlayer
	 * @param filepath
	 */
	public void readCommands(String filepath) {
		
	}
	/**
	 * Writes the current state of the Map to the file that the GamePlayer can then grab information from
	 * @param filepath
	 */
	public void writeState(String filepath) {
		
	}
}
