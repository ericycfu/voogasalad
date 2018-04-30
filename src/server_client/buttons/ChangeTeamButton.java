package server_client.buttons;
/**
 * Button responsible for changing teams in a lobby.
 * @author andrew
 */
import javafx.scene.control.Button;

public class ChangeTeamButton extends Button {
	public ChangeTeamButton() {
		this.getStyleClass().add("lobby_selection_button");
		this.setText("Change Team");
	}
}
