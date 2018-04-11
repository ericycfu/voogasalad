package gui_elements.buttons;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_object.GameObject;
import game_player.GamePlayer;
import game_view.buttons.ImageButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;
=======
import javafx.scene.control.Button;
>>>>>>> 493bb5f18a1fed1c2e02c29a7e85fcbf75200463

public class PlayGameButton extends ImageButton {
	
	public PlayGameButton(Stage stage) {
		setupText();
		this.setOnAction(e -> {
			//TO-DO
		});
	}
		
	private void setupText() {
		this.setText("Play Game");
	}
}
