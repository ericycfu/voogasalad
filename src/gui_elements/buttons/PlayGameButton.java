package gui_elements.buttons;

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
