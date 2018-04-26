package gui_elements.buttons;

import javafx.stage.Stage;

public class PlayGameButton extends ImageButton {	
	public PlayGameButton() {
		setupText();
	}
	
	public PlayGameButton(Stage stage) {
		setupText();
	//	this.setOnAction(e -> );
	}
	
	private void setupText() {
		this.getStyleClass().add("make_game_button");
		this.setText("Play Game");
	}
	
}
