package server;
/**
 * This class denotes a lobby of players tied to a particular GameInstance
 */
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

import game_engine.GameInstance;

public class GameLobby implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient List<Socket>[] myPlayers;
	private transient GameInstance loadedMap;
	private transient Map<Socket, Integer> playerIDs;
	private String mapName;
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
		mapName = toRun.getName();
		loadedMap = toRun;
		isRunning = false;
		
		addPlayer(lobbyHost);
	}
	/**
	 * Overrides Java serialization for this object
	 * @param out
	 * @throws IOException
	 */
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
	 }
	 @SuppressWarnings("unchecked")
	 /**
	  * Overrides Java deserialization for this object
	  * @param in
	  * @throws IOException
	  * @throws ClassNotFoundException
	  */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		 in.defaultReadObject();
		List<Integer>[] playersPerTeam = (List<Integer>[])in.readObject();
		 myPlayers = (List<Socket>[]) new ArrayList[numTeams];
		 for(int x = 0; x < playersPerTeam.length; x++) {
			 myPlayers[x] = new ArrayList<>();
			 for(int y = 0; y < playersPerTeam[x].size(); y++) {
				 myPlayers[x].add(null);
			 }
		 }

	}
	 /**
	  * Returns the team number that the player is on, 0 if team not found
	  * @param s
	  * @return
	  */
	public int getTeamID(Socket s) {
		for(int x = 0; x < myPlayers.length; x++) {
			if(myPlayers[x].contains(s))
				return x+1;
		}
		return -1;
	}
	/**
	 * Returns the individual ID associated with the player
	 * @param s
	 * @return
	 */
	public int getPlayerID(Socket s) {
		return playerIDs.get(s);
	}
	/**
	 * Adds the given player to the team with the least 
	 * @param toAdd
	 */
	public void addPlayer(Socket toAdd) {
		playerIDs.put(toAdd, nextID);
		nextID++;
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
	/**
	 * Adds a player to the specified team number
	 * @param index index of team to add to
	 * @param toAdd player to add
	 */
	public void addPlayer(int index, Socket toAdd) {
		if(!isRunning)
			myPlayers[index].add(toAdd);
	}
	/**
	 * Removes player from the lobby and from the ID list
	 * @param toRemove
	 */
	public void remove(Socket toRemove) {
		playerIDs.remove(toRemove);
		removeFromTeam(toRemove);
	}
	/**
	 * Remove player from the specific team
	 * @param toRemove player to remove
	 */
	public void removeFromTeam(Socket toRemove) {
		for(int x = 0; x < numTeams; x++) {
			if(myPlayers[x].contains(toRemove)) {
				myPlayers[x].remove(toRemove);
			}
		}
		
	}
	/**
	 * Changes the team of the given player
	 * @param newTeam new team ID
	 * @param toAdd the player changing
	 */
	public void changeTeam(int newTeam, Socket toAdd) {
		removeFromTeam(toAdd);
		addPlayer(newTeam-1,toAdd);
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
		return playerIDs.keySet().contains(s);
	}
	/**
	 * Returns the host of the team, who is the person who can start the game.
	 * Returns null if the lobby is empty
	 * @return
	 */
	public Socket getHost() {
		int min_ID = Collections.min(playerIDs.values());
		for(Socket s: playerIDs.keySet())
			if(playerIDs.get(s) == min_ID)
				return s;
		return null;
	}
	/**
	 * Checks whether 
	 * @param team_ID
	 * @return true if team has no players, false otherwise
	 */
	public boolean isTeamEmpty(int team_ID) {
		return myPlayers[team_ID-1].isEmpty();
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
	public int getNumPlayers(int team_ID) {
		return myPlayers[team_ID-1].size();
	}
	public String getMapName() {
		return mapName;
	}
}
