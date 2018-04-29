package server_client.screens;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import server.GameLobby;

public class LobbyDisplay extends GridPane {
	private int ID;
	public LobbyDisplay() {
		setPrefHeight(100);
		setPrefWidth(300);
		setHgap(0);
	    setVgap(0);
	    setPadding(new Insets(0, 10, 0, 10));
	}
	public void update(GameLobby serverInfo) {
		display(serverInfo);
	}
	private void display(GameLobby toDisplay) {
		getChildren().clear();
		ID = toDisplay.getID();
		Text lobbyText = new Text("LOBBY: " + ID);
		lobbyText.setId("lobby_main_text");
		add(lobbyText,0,0);
		Text teamText = new Text("Teams: " + toDisplay.getNumTeams());
		teamText.setId("lobby_info_text");
		add(lobbyText,0,1);
		Text playerText = new Text("Players: " + toDisplay.getCurrentSize());
		teamText.setId("team_text");
		add(playerText,1,1);
		add(new ImageView(SwingFXUtils.toFXImage(toDisplay.getCurrentGameInstance().getBackground(),null)),2,0);
	}
	public int getID() {
		return ID;
	}
}