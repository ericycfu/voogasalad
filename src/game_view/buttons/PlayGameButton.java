package game_view.buttons;

import javafx.scene.control.Button;

public class PlayGameButton extends ImageButton {
	public PlayGameButton() {
		setupText();
	}
		
	private void setupText() {
		this.setText("Play Game");
	}
}
