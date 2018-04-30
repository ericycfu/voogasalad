package server_client.buttons;
/**
 * Responsible for starting the game from a lobby
 * @author andrew
 */
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.scene.control.Button;

public class PlayButton extends Button {
	public PlayButton(ObjectOutputStream out) {
		this.getStyleClass().add("lobby_selection_button");
		this.setText("Play");
		this.setOnAction(e -> {
			try {
				out.writeObject("Play");
				out.flush();
			} catch (IOException e1) {
			}
	});
	}
}
