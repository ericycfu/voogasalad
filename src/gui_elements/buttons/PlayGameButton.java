package gui_elements.buttons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_object.GameObject;
import game_player.GamePlayer;
import game_view.buttons.ImageButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
