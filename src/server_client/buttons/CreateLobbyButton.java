package server_client.buttons;
/**
 * Button responsible for creating a new lobby.
 * @author andrew
 */
import javafx.scene.control.Button;

public class CreateLobbyButton extends Button {
	public CreateLobbyButton() {
		this.getStyleClass().add("lobby_selection_button");
		this.setText("Create Lobby");
	}
	
	
}
