package game_engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import authoring.backend.MapSettings;
import game_data.Reader;
import game_data.Writer;
import game_object.GameObject;
import game_object.GameObjectManager;
import pathfinding.GridMap;
import transform_library.Transform;
import transform_library.Vector2;

/**
 * Codes for an instance of a game that is currently being run
 * @author andrew
 *
 */
public class GameInstance implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * Sets up the GameInstance based on the information in the file
	 * @param filepath
	 */
	private boolean running;
	private GameInfo myGameInfo;
	private GameObjectManager myObjectManager;
	private TeamManager myTeamManager;
	private transient BufferedImage background;
	private double gameTime;
	private List<ChatEntry> chat;
	private double mapHeight;
	private double mapWidth;

	public GameInstance(GameInfo g, GameObjectManager gom, String filepath) {

		myGameInfo = g;
		myObjectManager = gom;
		myTeamManager = new TeamManager();
		chat = new ArrayList<ChatEntry>();
		running = false;
		try {
			setUp(filepath);
		}
		catch(Exception e) {e.printStackTrace();}
	}
	public GameInstance(BufferedImage i) {
		background = i;
	}
	public void setUp(String filepath) throws ClassNotFoundException, IOException {
		MapSettings mapProperties = (MapSettings)Reader.read(filepath).get(2);
		System.out.println(mapProperties.getNumPlayers());
		for(int x = 0; x < mapProperties.getNumPlayers(); x++) {
			ResourceManager rm = new ResourceManager();
			for(String s: mapProperties.getInitialResources().keySet()) {
				rm.addResource(s, mapProperties.getInitialResources().get(s));
			}
			myTeamManager.createTeam("Team" + Integer.toString(x+1), rm);
		}
		mapHeight = mapProperties.getMapHeight();
		mapWidth = mapProperties.getMapWidth();
		background = ImageIO.read(this.getClass().getResourceAsStream(mapProperties.getImagePath()));
		if(background == null)
			System.out.println("The Image is null");
		else System.out.println("Image successfully loaded");
	}
	/**
	 * Saves the information in the GameInstance to the specified file
	 * @param filepath
	 * @throws IOException 
	 */
	public void save(String filepath) throws IOException {
		List<Object> toWrite = new ArrayList<>();
		toWrite.add(this);
		Writer.write(filepath,toWrite);
	}
	private void writeObject(ObjectOutputStream out) throws IOException{
		out.defaultWriteObject();
		ImageIO.write(getBackground(), "png", out);
	}
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		background = ImageIO.read(in);
	}

	/**
	 * Read commands from the file that is updated by the GamePlayer
	 * @param filepath
	 */
	public void executeCommand(int source_id, int target_id, int interaction_ID, int xcor, int ycor) {
		if(!running)
			return;
		GridMap currentGridMap = new GridMap(background.getHeight(), background.getWidth());
		myObjectManager.getGameObject(source_id).queueInteraction(myObjectManager.getGameObject(target_id), interaction_ID, myObjectManager, currentGridMap, new Vector2(xcor,ycor));
	}

	public void executeBuildCommand(int sourceID, String newUnitName, int interactionID, int xcor, int ycor) {
		if(!running)
			return;
		GameObject newGO = getGameInfo().get(newUnitName);
		newGO.setTransform(new Transform(new Vector2(xcor,ycor)));
		GameObject source = myObjectManager.getGameObject(sourceID);
		Vector2 direction = new Vector2(xcor,ycor);
		source.queueInteraction(newGO, interactionID, myObjectManager, getNewGridMap(), direction);
	}
	public void executeMovement(int id, double xcor, double ycor) {
		if(!running)
			return;
		Vector2 v = new Vector2(xcor,ycor);
		myObjectManager.getGameObject(id).queueMovement(v,myObjectManager,getNewGridMap());
	}
	public GridMap getNewGridMap() {
		return new GridMap(mapHeight, mapWidth);
	}
	public void addToChat(int player_ID, String message) {
		chat.add(new ChatEntry(gameTime,player_ID,message));
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
	public List<ChatEntry> getChat(){
		return chat;
	}
}
