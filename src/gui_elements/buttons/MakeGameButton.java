package gui_elements.buttons;

import authoring.view.MakeGameSelect;
import javafx.stage.Stage;

public class MakeGameButton extends ImageButton {
	public MakeGameButton() {
		setupText();
	}
	
	public MakeGameButton(Stage stage) {
		setupText();
		this.setOnAction(e -> new MakeGameSelect(stage));
	}
	
	private void setupText() {
		this.getStyleClass().add("make_game_button");
		this.setText("Make Game");
	}
	
	
}
