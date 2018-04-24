package game_engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game_data.Reader;
import game_data.Writer;
import game_object.GameObject;
import game_object.GameObjectManager;
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
	private transient TeamManager myTeamManager;
	private transient Reader myReader;
	private transient Writer myWriter;
	private BufferedImage background;
	private double gameTime;
	
	public GameInstance(GameInfo g, String filepath) {
		
		myReader = new Reader();
		myGameInfo = g;
		myTeamManager = new TeamManager();
		try {
		setUp(filepath);
		}
		catch(Exception e) {}
		play();
	}
	public GameInstance(BufferedImage i) {
		background = i;
	}
	public void setUp(String filepath) throws ClassNotFoundException, IOException {
		List<Object> gameObjects = myReader.read(filepath, "gameObject");
		myObjectManager = new GameObjectManager();
		for(int x = 0; x < gameObjects.size(); x++) {
			myObjectManager.addElement((GameObject)gameObjects.get(x));
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
	public void executeCommand(int source_id, int target_id, int interaction_ID) {
		if(!running)
			return;
		myObjectManager.getGameObject(source_id).queueInteraction(myObjectManager.getGameObject(target_id), interaction_ID, myObjectManager);
	}
	
	public void executeMovement(int id, double xcor, double ycor) {
		if(!running)
			return;
		Vector2 v = new Vector2(xcor,ycor);
		myObjectManager.getGameObject(id).queueMovement(v,myObjectManager);
	}
	
	public void loop() {
		double startTime = 0;
		double endTime = 0;
		double diff = 0;
		while(running) {
			startTime = System.nanoTime();
			myObjectManager.runGameObjectLoop(diff);
			gameTime += diff;
			endTime = System.nanoTime();
			diff = endTime - startTime;
		}
	}
	public void play() {
		running = true;
		loop();
	}
	public void stop() {
		running = false;
	}
	public boolean getIsRunning() {
		return running;
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
	public BufferedImage getBackground() {
		return background;
	}
	public double getGameTime(){
		return gameTime;
	}
}
