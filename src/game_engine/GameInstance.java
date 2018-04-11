package game_engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game_data.Reader;
import game_data.Writer;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.UnmodifiableGameObjectException;
import game_player.GamePlayer;
import interactions.Interaction;
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
	private Reader myReader;
	private Writer myWriter;
	
	public GameInstance(GameInfo g, String filepath) {
		myReader = new Reader();
		myGameInfo = g;
		myTeamManager = new TeamManager();
		setUp(filepath);
		GamePlayer player1 = new GamePlayer(this, 1);
		play();
	}
	public void setUp(String filepath) throws ClassNotFoundException, IOException {
		List<Object> gameObjects = myReader.read(filepath, "gameObject");
		myObjectManager = new GameObjectManager();
		for(int x = 0; x < gameObjects.size(); x++) {
			myObjectManager.addElementToManager((GameObject)gameObjects.get(x));
		}
	}
	/**
	 * Saves the information in the GameInstance to the specified file
	 * @param filepath
	 * @throws IOException 
	 */
	public void save(String filepath) throws IOException {
		List<Object> toWrite = new ArrayList<>();
		toWrite.add(this);
		myWriter.write(filepath,toWrite);
	}
	/**
	 * Read commands from the file that is updated by the GamePlayer
	 * @param filepath
	 */
	public void executeCommand(int source_id, int target_id, Interaction i) {
		if(!running)
			return;
		myObjectManager.get(source_id).queueInteraction(myObjectManager.get(target_id));
		try {
			myObjectManager.get(source_id).accessLogic().setCurrentInteraction(i);
		} catch (UnmodifiableGameObjectException e) {
		}
	}
	public void executeMovement(int id, int xcor, int ycor) {
		if(!running)
			return;
		Vector2 v = new Vector2(xcor,ycor);
		myObjectManager.get(id).queueMovement(v);
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
	public GameInfo getGameInfo() {
		return myGameInfo;
	}
	public GameObjectManager getGameObjects() {
		return myObjectManager;
	}
	public TeamManager getTeamManager() {
		return myTeamManager;
	}
}
