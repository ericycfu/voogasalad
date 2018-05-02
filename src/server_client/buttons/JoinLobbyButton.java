package server_client.buttons;

import javafx.scene.control.Button;
/**
 * Button responsible for joining a lobby
 * @author andrew
 */
public class JoinLobbyButton extends Button {
	public JoinLobbyButton() {
		this.getStyleClass().add("lobby_selection_button");
		this.setText("Join Lobby");
	}
	
	
}