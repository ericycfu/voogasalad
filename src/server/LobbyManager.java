package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.ElementManager;

public class LobbyManager implements ElementManager<GameLobby>{
	private Map<Integer,GameLobby> lobbies;
	public LobbyManager() {
		lobbies = new HashMap<>();
	}
	@Override
	public int addElementToManager(GameLobby element) {
		int id = lobbies.size() + 1;
		lobbies.put(id, element);
		return id;
	}

	@Override
	public void removeElement(GameLobby element) {
		lobbies.remove(element.getID());
		
	}

	@Override
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

}
