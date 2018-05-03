package server_client.screens.display;
import javafx.scene.text.Text;
import server.GameLobby;

public class TeamDisplay extends GridPaneDisplay {
	private int ID;
	private int numPlayers;
	private Text lobbyText;
	public TeamDisplay(int ID) {
		super();
	    this.ID = ID;
	    numPlayers = -1;
	    lobbyText = new Text();
	    lobbyText.setId("lobby_main_text");
	    add(lobbyText,0,0);
	}
	/**
	 * Updates this object to reflect information from the Lobby
	 * @param toDisplay
	 */
	public void update(GameLobby toDisplay) {
		numPlayers = toDisplay.getNumPlayers(ID);
		lobbyText.setText("TEAM " + ID + "	 Players: " + numPlayers);

	}
	public int getID() {
		return ID;
	}
	public int getNumPlayers() {
		return numPlayers;
	}
}