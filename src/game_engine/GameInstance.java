package game_engine;

import java.util.List;

import game_data.Reader;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_player.GamePlayer;

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
	private GameObjectManager myObjectManager;
	private PlayerManager myPlayerManager;
	private SceneManager mySceneManager;
	private Reader myReader;
	
	public GameInstance(GameInfo g, String filepath) {
		myReader = new Reader();
		myGameInfo = g;
		setUp(filepath);
		GamePlayer player1 = new GamePlayer(myGameInfo);
		GamePlayer player2 = new GamePlayer(myGameInfo);
		
	}
	public void setUp(String filepath) {
		List<GameObject> gameObjects = myReader.read(INITIALIZATION_LOCATION, "gameObject");
		myObjectManager = new GameObjectManager();
		for(int x = 0; x < gameObjects.size(); x++) {
			myObjectManager.addElementToManager(gameObjects.get(x));
		}
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
