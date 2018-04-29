package server;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LobbyManager implements Serializable{
	/**
	 * This entity stores all GameLobbies currently active.
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer,GameLobby> lobbies;
	private int nextID;
	public LobbyManager() {
		lobbies = new HashMap<>();
		nextID = 0;
	}
	public int addElementToManager(GameLobby element) {
		nextID++;
		element.setID(nextID);
		lobbies.put(nextID, element);
		return nextID;
	}

	public void removeElement(GameLobby element) {
		lobbies.remove(element.getID());	
	}

	public List<GameLobby> getElements() {
		List<GameLobby> gameLobbies = new ArrayList<>();
		for(GameLobby g: lobbies.values())
		{
			gameLobbies.add(g);
		}
		return Collections.unmodifiableList(gameLobbies);
	}
	public GameLobby get(int ID) {
		return lobbies.get(ID);
	}
	public GameLobby find(Socket player) {
		for(GameLobby g: lobbies.values()) {
			if (g.contains(player))
				return g;
		}
		return null;
	}

}
