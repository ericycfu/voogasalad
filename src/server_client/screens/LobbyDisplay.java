package server_client.screens;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import server.GameLobby;

public class LobbyDisplay extends GridPane {
	public LobbyDisplay() {
		setPrefHeight(100);
		setPrefWidth(300);
		setHgap(10);
	    setVgap(10);
	    setPadding(new Insets(0, 10, 0, 10));
	}
	public void update(GameLobby serverInfo) {
		display(serverInfo);
	}
	private void display(GameLobby toDisplay) {
		getChildren().clear();
		Text lobbyText = new Text("LOBBY: " + toDisplay.getID());
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
}