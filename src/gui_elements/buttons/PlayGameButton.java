package gui_elements.buttons;

import game_view.buttons.ImageButton;
import javafx.scene.control.Button;

public class PlayGameButton extends ImageButton {
	public PlayGameButton() {
		setupText();
	}
		
	private void setupText() {
		this.setText("Play Game");
	}
}
