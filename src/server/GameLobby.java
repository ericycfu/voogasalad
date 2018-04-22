package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import game_engine.GameInstance;

public class GameLobby implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient List<Socket> myPlayers;
	private transient GameInstance loadedMap;
	private boolean isRunning;
	private int ID;
	
	public GameLobby(Socket lobbyHost, GameInstance toRun) {
		myPlayers = new ArrayList<Socket>();
		myPlayers.add(lobbyHost);
		loadedMap = toRun;
		isRunning = false;
	}
	 private void writeObject(ObjectOutputStream out) throws IOException{
		 out.defaultWriteObject();
		 out.writeInt(myPlayers.size());
		 //
		 ImageIO.write(loadedMap.getBackground(), "png", out);
	 }
	 private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		 in.defaultReadObject();
		 for(int x = 0; x < in.readInt(); x++)
			 myPlayers.add(null);
		 loadedMap = new GameInstance(ImageIO.read(in));
	 }
	public void addPlayer(Socket toAdd) {
		if(!isRunning && loadedMap.getTeamManager().getElements().size() > myPlayers.size())
		myPlayers.add(toAdd);
	}
	public void removePlayer(Socket toRemove) {
		myPlayers.remove(toRemove);
	}
	public void setID(int newID) {
		ID = newID;
	}
	public int getID() {
		return ID;
	}
	public void run() {
		isRunning = true;
	}
	public int getCurrentSize() {
		return myPlayers.size();
	}
	public boolean contains(Socket s) {
		return myPlayers.contains(s);
	}
	public Object getHost() {
		return myPlayers.get(0);
	}
	public boolean isRunning() {
		return isRunning;
	}
	public void setIsRunning(boolean b) {
		isRunning = b;
	}
	public GameInstance getCurrentGameInstance() {
		return loadedMap;
	}
}
