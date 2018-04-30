package server_client.screens;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import server.GameLobby;

public class TeamDisplay extends GridPane {
	private int ID;
	public TeamDisplay(int ID) {
		setPrefHeight(100);
		setPrefWidth(700);
		setHgap(0);
	    setVgap(0);
	    setPadding(new Insets(10, 10, 10, 10));
	    this.ID = ID;
	}
	public void update(GameLobby toDisplay) {
		getChildren().clear();
		Text lobbyText = new Text("TEAM " + ID + "	 Players: " + toDisplay.getNumPlayers(ID));
		lobbyText.setId("lobby_main_text");
		add(lobbyText,0,0);

	}
	public int getID() {
		return ID;
	}
}