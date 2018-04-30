package server_client.buttons;
/**
 * Button responsible for leaving a lobby
 * @author andrew
 */
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.scene.control.Button;

public class LeaveLobbyButton extends Button {
	public LeaveLobbyButton(ObjectOutputStream out) {
		getStyleClass().add("lobby_selection_button");
		setText("Leave");
		this.setOnAction(e -> {
				try {
					out.writeObject("Leave");
					out.flush();
				} catch (IOException e1) {
				}
		});
	}
}
