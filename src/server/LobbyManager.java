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
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer,GameLobby> lobbies;
	public LobbyManager() {
		lobbies = new HashMap<>();
	}
	public int addElementToManager(GameLobby element) {
		int id = lobbies.size() + 1;
		lobbies.put(id, element);
		return id;
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
