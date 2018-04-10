package game_engine;

import java.util.List;

import game_data.Reader;
import game_data.Writer;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_player.GamePlayer;
import interactions.Interaction;
import transform_library.Transform;
import transform_library.Vector2;

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
	private boolean running;
	private GameInfo myGameInfo;
	private GameObjectManager myObjectManager;
	private TeamManager myTeamManager;
	private SceneManager mySceneManager;
	private Reader myReader;
	private Writer myWriter;
	
	public GameInstance(GameInfo g, String filepath) {
		myReader = new Reader();
		myWriter = new Writer();
		myGameInfo = g;
		setUp(filepath);
		TeamManager teamManage1 = new TeamManager();
		TeamManager teamManage2 = new TeamManager();
		GamePlayer player1 = new GamePlayer(myGameInfo, myObjectManager, teamManage1);
		GamePlayer player2 = new GamePlayer(myGameInfo, myObjectManager, teamManage2);
		play();
	}
	public void setUp(String filepath) {
		List<Object> gameObjects = myReader.read(filepath, "gameObject");
		myObjectManager = new GameObjectManager();
		for(int x = 0; x < gameObjects.size(); x++) {
			myObjectManager.addElementToManager((GameObject)gameObjects.get(x));
		}
		mySceneManager = (SceneManager) myReader.read(filepath,"SceneManager");
	}
	/**
	 * Saves the information in the GameInstance to the specified file
	 * @param filepath
	 */
	public void save(String filepath) {
		myWriter.write(filepath,myObjectManager.getElements());
		myWriter.write(filepath,mySceneManager);
	}
	/**
	 * Read commands from the file that is updated by the GamePlayer
	 * @param filepath
	 */
	public void executeCommand(int source_id, int target_id, Interaction i) {
		if(!running)
			return;
		myObjectManager.get(source_id).queueInteraction(myObjectManager.get(target_id));
		myObjectManager.get(source_id).accessLogic().setCurrentInteraction(i);
	}
	public void executeMovement(int id, int xcor, int ycor) {
		if(!running)
			return;
		Transform t = new Transform(new Vector2(xcor,ycor));
		myObjectManager.get(id).setTransform(t);
	}
	public void loop() {
		while(running) {
			myObjectManager.runGameObjectLoop();
		}
	}
	public void play() {
		running = true;
		loop();
	}
	public void stop() {
		running = false;
	}
}
