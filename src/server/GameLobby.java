package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import game_engine.GameInstance;

public class GameLobby {
	private List<Socket> myPlayers;
	private GameInstance loadedMap;
	private boolean isRunning;
	private int ID;
	
	public GameLobby(Socket lobbyHost, GameInstance toRun) {
		myPlayers = new ArrayList<Socket>();
		myPlayers.add(lobbyHost);
		loadedMap = toRun;
		isRunning = false;
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
}
