package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import game_engine.GameInstance;

public class GameLobby implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient List<Socket>[] myPlayers;
	private transient GameInstance loadedMap;
	private transient Map<Socket, Integer> playerIDs;
	private int nextID;
	private boolean isRunning;
	private int ID;
	private int numTeams;
	
	@SuppressWarnings("unchecked")
	public GameLobby(Socket lobbyHost, GameInstance toRun) {
		numTeams = toRun.getTeamManager().getSize();
		myPlayers = (List<Socket>[]) new ArrayList[numTeams];
		for(int x = 0; x < numTeams; x++){
			myPlayers[x] = new ArrayList<>();
		}
		playerIDs = new HashMap<>();
		nextID = 1;
		
		loadedMap = toRun;
		isRunning = false;
		
		addPlayer(lobbyHost);
	}
	 @SuppressWarnings("unchecked")
	private void writeObject(ObjectOutputStream out) throws IOException{
		 out.defaultWriteObject();
		 List<Integer>[] numPlayers = (List<Integer>[])new ArrayList[numTeams];
		 for(int x = 0; x < numTeams; x++) {
			 numPlayers[x] = new ArrayList<>();
			 for(Socket s: myPlayers[x])
				 numPlayers[x].add(playerIDs.get(s));
		 }
		 out.writeObject(numPlayers);
		 ImageIO.write(loadedMap.getBackground(), "png", out);
	 }
	 private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		 in.defaultReadObject();
		 @SuppressWarnings("unchecked")
		List<Integer>[] playersPerTeam = (List<Integer>[])in.readObject();
		 for(int x = 0; x < in.readInt(); x++) {
			 myPlayers[x] = new ArrayList<>();
			 for(int y = 0; y < playersPerTeam[x].size(); y++) {
				 myPlayers[x].add(null);
			 }
		 }

		 loadedMap = new GameInstance(ImageIO.read(in));
	}
	public int getTeamID(Socket s) {
		for(int x = 0; x < myPlayers.length; x++) {
			if(myPlayers[x].contains(s))
				return x;
		}
		return -1;
	}
	public int getPlayerID(Socket s) {
		return playerIDs.get(s);
	}
	public void addPlayer(Socket toAdd) {
		playerIDs.put(toAdd, nextID++);
		if(isRunning)
			return;
		int min_index = 0;
		for(int y = 1; y < numTeams; y++) {
			if(myPlayers[y].size() < myPlayers[min_index].size()) {
				min_index = y;
			}
		}
		addPlayer(min_index, toAdd);
		
	}
	public void addPlayer(int index, Socket toAdd) {
		if(!isRunning)
			myPlayers[index].add(toAdd);
	}
	public void removePlayer(Socket toRemove) {
		for(int x = 0; x < numTeams; x++) {
			if(myPlayers[x].contains(toRemove))
				myPlayers[x].remove(toRemove);
		}
		playerIDs.remove(toRemove);
	}
	public void changeTeam(int newTeam, Socket toAdd) {
		removePlayer(toAdd);
		addPlayer(newTeam,toAdd);
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
		return playerIDs.size();
	}
	public boolean contains(Socket s) {
		for(int x = 0; x < numTeams; x++) {
			if(myPlayers[x].contains(s))
				return true;
		}
		return false;
	}
	public Socket getHost() {
		int min_ID = Collections.min(playerIDs.values());
		for(Socket s: playerIDs.keySet())
			if(playerIDs.get(s) == min_ID)
				return s;
		return null;
	}
	public boolean isTeamEmpty(int team_ID) {
		return myPlayers[team_ID].isEmpty();
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
	public int getNumTeams() {
		return numTeams;
	}
}
