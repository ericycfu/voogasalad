package server_client.screens.display;
import javafx.scene.text.Text;
import server.GameLobby;

public class LobbyDisplay extends GridPaneDisplay {
	private int ID;
	public LobbyDisplay() {
		super();
	}
	/**
	 * Updates this object to reflect the information in the GameLobby
	 * @param toDisplay the new GameLobby
	 */
	public void update(GameLobby toDisplay) {
		getChildren().clear();
		ID = toDisplay.getID();
		Text lobbyText = new Text("[LOBBY " + ID + "]	 Map: " + toDisplay.getMapName() + "		Teams: " + toDisplay.getNumTeams());
		lobbyText.setId("lobby_main_text");
		add(lobbyText,0,0);
	}
	/**
	 * Returns the ID of the lobby that last updated this object
	 * @return the ID of the last GameLobby passed into update()
	 */
	public int getID() {
		return ID;
	}
}