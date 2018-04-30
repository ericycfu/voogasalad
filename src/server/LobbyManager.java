package server;
/**
 * This entity stores all GameLobbies currently active.
 * @author andrew
 */
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LobbyManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Map<Integer,GameLobby> lobbies;
	private int nextID;
	public LobbyManager() {
		lobbies = new HashMap<>();
		nextID = 0;
	}
	/**
	 * Adds the GameLobby to the manager and assigns it an ID
	 * @param element gameLobby to add
	 */
	public void addElementToManager(GameLobby element) {
		nextID++;
		element.setID(nextID);
		lobbies.put(nextID, element);
	}
	/**
	 * Removes the given GameLobby from this object
	 * @param element GameLobby to remove
	 */
	public void removeElement(GameLobby element) {
		lobbies.remove(element.getID());	
	}
	/**
	 * returns an unmodifiable list of all GameLobbies
	 * @return
	 */
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
	/**
	 * Finds the GameLobby
	 * @param player the player to find
	 * @return the GameLobby containing the player, null if no active lobbies contain the player
	 */
	public GameLobby find(Socket player) {
		for(GameLobby g: lobbies.values()) {
			if (g.contains(player))
				return g;
		}
		return null;
	}

}
