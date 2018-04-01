package game_view.buttons;

import javafx.scene.control.Button;

public class PlayGameButton extends Button {
	public PlayGameButton() {
		setupText();
	}
		
	private void setupText() {
		this.setText("Play Game");
	}
}
