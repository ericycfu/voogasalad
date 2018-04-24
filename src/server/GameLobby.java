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
	private transient List<Socket>[] myPlayers;
	private transient GameInstance loadedMap;
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
		myPlayers[0].add(lobbyHost);
		loadedMap = toRun;
		isRunning = false;
	}
	 private void writeObject(ObjectOutputStream out) throws IOException{
		 out.defaultWriteObject();
		 int[] numPlayers = new int[numTeams];
		 for(int x = 0; x < numTeams; x++) {
			 numPlayers[x] = myPlayers[x].size();
		 }
		 out.writeObject(numPlayers);
		 ImageIO.write(loadedMap.getBackground(), "png", out);
	 }
	 private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		 in.defaultReadObject();
		 int[] playersPerTeam = (int[])in.readObject();
		 for(int x = 0; x < in.readInt(); x++) {
			 myPlayers[x] = new ArrayList<>();
			 for(int y = 0; y < playersPerTeam[x]; y++) {
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
		int playerID = 1;
		for(int x = 0; x < myPlayers.length; x++) {
			for(int y = 0; y < myPlayers[x].size(); y++) {
				if(myPlayers[x].get(y).equals(s))
					return playerID;
				playerID++;
			}
		}
		return -1;
	}
	public void addPlayer(Socket toAdd) {
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
		int total = 0;
		for(int x = 0; x < numTeams; x++)
			total += myPlayers[x].size();
		return total;
	}
	public boolean contains(Socket s) {
		for(int x = 0; x < numTeams; x++) {
			if(myPlayers[x].contains(s))
				return true;
		}
		return false;
	}
	public Socket getHost() {
		for(int x = 0; x < numTeams; x++) {
			if(!myPlayers[x].isEmpty())
				return myPlayers[x].get(0);
		}
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
}
