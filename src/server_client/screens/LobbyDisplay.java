package server_client.screens;
/**
 * Displays the information for a particular lobby in the LobbyManager
 * @author andrew
 */
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import server.GameLobby;

public class LobbyDisplay extends GridPane {
	private int ID;
	public LobbyDisplay() {
		setPrefHeight(100);
		setPrefWidth(800);
		setHgap(0);
	    setVgap(0);

	    setPadding(new Insets(10, 10, 10, 10));
	}
	/**
	 * Updates this object to reflect the information in the GameLobby
	 * @param toDisplay the new GameLobby
	 */
	public void update(GameLobby toDisplay) {
		getChildren().clear();
		ID = toDisplay.getID();
		Text lobbyText = new Text("LOBBY " + ID + "			Teams: " + toDisplay.getNumTeams());
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